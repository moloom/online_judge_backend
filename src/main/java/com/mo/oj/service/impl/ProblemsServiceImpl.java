package com.mo.oj.service.impl;

import com.mo.oj.mapper.ProblemsMapper;
import com.mo.oj.mapper.SubmissionMapper;
import com.mo.oj.pojo.*;
import com.mo.oj.service.ProblemsService;
import com.mo.oj.utils.JudgeService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ProblemsServiceImpl implements ProblemsService {

    @Resource
    ProblemsMapper problemsMapper;

    @Resource
    SubmissionMapper submissionMapper;

    //初始化线程池
    ExecutorService executorService = Executors.newFixedThreadPool(20);

    /**
     * 查询所有的标签, unless = "#result==null"
     *
     * @return
     */
    @Cacheable(value = "tag", key = "'searchTagAll'", unless = "#result == null")
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public List<Tag> searchTagAll() {
        return this.problemsMapper.searchTagAll();
    }

    /**
     * 查询标签，包括权值
     *
     * @return
     */
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public List<Tag> searchTagCloudList() {
        List<Tag> tagList = this.problemsMapper.searchTagAll();
        //给予标签权值，先把值写死
        for (int i = 0; i < tagList.size(); ++i) {
            tagList.get(i).setValue("" + (i + 10));
        }
        return tagList;
    }

    /**
     * 查询题目list，条件：difficulty、state、tag、title or id，注：id也是要模糊查询的
     *
     * @param difficulty
     * @param status
     * @param tag
     * @param keyword
     * @param user_id
     * @return
     */
    @Transactional(readOnly = true, timeout = 20)
    @Override
    public List<Problem> searchProblemListByConditions(Integer difficulty, Integer status, Integer tag, String keyword, Integer user_id, Integer start) {
        /*已解决的：直接查提交记录表，
         * 尝试过的，也是直接查提交记录表，查出 状态是非通过的，且无通过记录
         * 未开始的，排除提交表中，做过的题目，*/
        //查询已解决的
        List<Problem> problemList = this.problemsMapper.searchProblemListByCondition(difficulty, status, tag, keyword, user_id, start);
        return problemList;
    }

    /**
     * 查询题目的数量，条件：user_id，status,difficulty,tag,keyword,user_id
     *
     * @param difficulty
     * @param status
     * @param tag
     * @param keyword
     * @param user_id
     * @return
     */
    @Transactional(readOnly = true, timeout = 20)
    @Override
    public Integer searchProblemCountByCondition(Integer difficulty, Integer status, Integer tag, String keyword, Integer user_id) {
        return this.problemsMapper.searchProblemCountByCondition(difficulty, status, tag, keyword, user_id);
    }

    /**
     * 查询一条problem信息，条件id
     *
     * @param id
     * @return
     */
    @Cacheable(value = "problem", key = "'searchProblemById'+#id", unless = "#result == null")
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public Problem searchProblemById(Integer id) {
        return this.problemsMapper.searchProblemById(id);
    }

    /**
     * 判断当前用户所选的题目是否被收藏
     *
     * @param userId
     * @param problemId
     * @return
     */
    @Cacheable(value = "favorite", key = "'isFavorite-userId'+#userId+'-problemId-'+#problemId", unless = "#result == null")
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public Boolean isFavorite(Integer userId, Integer problemId) {
        int count = this.problemsMapper.searchFavoriteCountByUserIdAndProblemId(userId, problemId);
        if (count == 0)
            return false;
        else
            return true;
    }

    /**
     * 查询当前题目的点赞或点踩信息
     *
     * @param problemGoodRecord
     * @return
     */
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public HashMap<String, Object> isGood(ProblemGoodRecord problemGoodRecord) {
        //查询problem_good_record表，看有没有点赞点踩的记录，
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("isGood", false);
        map.put("isBad", false);
        ProblemGoodRecord findProblemGoodRecord = this.problemsMapper.isGood(problemGoodRecord);
        if (findProblemGoodRecord == null)
            return map;
        if (findProblemGoodRecord.getNumber() > 0)
            map.replace("isGood", true);
        else
            map.replace("isBad", true);
        return map;
    }

    /**
     * 收藏或者取消收藏
     *
     * @param favorite
     * @param isFavorite
     * @return
     */
    @CacheEvict(value = "favorite", key = "'isFavorite-userId'+#favorite.create_by+'-problemId-'+#favorite.problem_id")
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, timeout = 20)
    @Override
    public Boolean updateFavorite(Favorite favorite, boolean isFavorite) {
        if (isFavorite) {
            int count = this.problemsMapper.deleteFavorite(favorite);
            if (count != 1)
                return false;
        } else {
            int count = this.problemsMapper.addFavorite(favorite);
            if (count != 1)
                return false;
        }
        return true;
    }

    /**
     * 点赞或点踩，或者取消点赞点踩操作。
     * 根据number值来辨别要做什么工作，
     * 值为1：是点赞操作，需要添加一条点赞记录，
     * 值为0：是取消点赞或点踩操作，直接删除数据库中的点赞或点踩信息就行
     * 值为-1：是点踩操作，添加一条点踩记录
     * <p>
     * 点赞或点踩会影响problem中的点赞和点踩数量，所以需要移除缓存的旧数据
     *
     * @param problemGoodRecord
     * @return
     */
    @CacheEvict(value = "problem", key = "'searchProblemById'+#problemGoodRecord.problem_id")
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, timeout = 20)
    @Override
    public Boolean updateGoodAndBad(ProblemGoodRecord problemGoodRecord) {
        int flag = flag = this.problemsMapper.deleteProblemGoodRecord(problemGoodRecord);
        int flag1 = 0;
        //点赞或点踩时插入一条记录到里面去
        if (problemGoodRecord.getNumber() == 1 || problemGoodRecord.getNumber() == -1)
            flag1 = this.problemsMapper.insertProblemGoodRecord(problemGoodRecord);
        //修改problem中的good、bad数量
        int flag2 = this.problemsMapper.updateProblemGoodAndBadNumber(problemGoodRecord.getProblem_id());
        //如果取消点赞点踩操作的 删除记录和修改点赞点踩数量成功，则返回true，
        if (problemGoodRecord.getNumber() == 0 && flag > 0 && flag2 > 0) {
            return true;
        }
        //如果点赞点踩操作的 插入记录和修改点赞点踩数量成功，则返回true，
        else if (problemGoodRecord.getNumber() != 0 && flag1 > 0 && flag2 > 0)
            return true;
        return false;
    }

    /**
     * 临时保存代码
     *
     * @param submission
     * @return
     */
    @CacheEvict(value = "submission", key = "'getCode-userId'+#submission.user_id+'-problemId-'+#submission.problem_id")
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, timeout = 15)
    @Override
    public Boolean saveCode(Submission submission) {
        //如果提交记录中没有状态为0的记录，则新增一条状态为0的记录，如果有则修改就行
        submission.setStatus(0);
        int flag = 0;
        Submission searchSubmission = this.submissionMapper.searchSubmissionByStatusAndUserIdAndProblemId(submission);
        if (searchSubmission == null) {
            flag = this.submissionMapper.insertSubmission(submission);
        } else {
            submission.setId(searchSubmission.getId());
            flag = this.submissionMapper.updateSubmission(submission);
        }
        if (flag > 0)
            return true;
        return false;
    }


    /**
     * 用户提交代码
     *
     * @param submission
     * @return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, timeout = 15)
    @Override
    public Integer submitCode(Submission submission) {
        //修改暂存的代码
        submission.setStatus(0);
        int flag = 0;
        Submission searchSubmission = this.submissionMapper.searchSubmissionByStatusAndUserIdAndProblemId(submission);
        if (searchSubmission == null) {
            flag = this.submissionMapper.insertSubmission(submission);
        } else {
            submission.setId(searchSubmission.getId());
            flag = this.submissionMapper.updateSubmission(submission);
        }
        try {
            String useCasePath = "/tem/useCaseDir/";
            File useCaseFile = new File(useCasePath + submission.getProblem_id());
            //如果测试用例文件夹不存在，则创建测试用例文件
            if (!useCaseFile.exists()) {
                useCaseFile.mkdir();
                List<UseCases> useCasesList = this.problemsMapper.searchUseCaseList(submission.getProblem_id());
                //循环写入测试用例文件
                for (int i = 0; i < useCasesList.size(); ++i) {
                    File in = new File(useCasePath + submission.getProblem_id() + "/" + i + 1 + ".in");
                    File out = new File(useCasePath + submission.getProblem_id() + "/" + i + 1 + ".out");
                    //创建文件
                    in.createNewFile();
                    out.createNewFile();

                    FileOutputStream fosIn = new FileOutputStream(in);
                    FileOutputStream fosOut = new FileOutputStream(out);
                    //写入内容到文件
                    fosIn.write(useCasesList.get(i).getInput().getBytes(StandardCharsets.UTF_8));
                    fosOut.write(useCasesList.get(i).getOutput().getBytes(StandardCharsets.UTF_8));
                    fosIn.close();
                    fosOut.close();
                }
                //写完后，生成data.conf
                Process execProcess = Runtime.getRuntime().exec("bash /tem/javaTest/judge/buildCaseDir " + useCaseFile);
                if (execProcess.waitFor() == 1) {
                    System.out.println("测试用例---data.conf生成失败！");
                    return null;
                }
            }
            //开启多线程判题
/*            FutureTask<Submission> futureTask = new FutureTask<Submission>(() -> {
                return new JudgeService().judge(submission);
            });
            executorService.submit(futureTask);
            //获取评测结果
            Submission submission1 = futureTask.get();*/
            JudgeService judgeService = new JudgeService();
            Submission submission1 = judgeService.judge(submission);

            if (submission1 == null)
                return null;
            //修改题目的一些数据
            Problem problem = this.problemsMapper.searchProblemById(submission1.getProblem_id());
            //判断是不是第一次提交这题，如果是就算进去
            int countOfTime = this.submissionMapper.isFirstTimes(submission1);
            if (countOfTime == 0)
                problem.setSubmit_number(problem.getSubmit_number() + 1);
            problem.setSubmit_times(problem.getSubmit_times() + 1);
            problem.setLatest_submitFlag(1);
            problem.setModify_by(submission1.getUser_id());

            submission1.setId(null);
            //新增一条提交信息
            int flag1 = this.submissionMapper.insertSubmission(submission1);
            //正常通过，修改一些数据
            if (submission1.getStatus() == 1) {
                //修改题目的通过人数量、通过次数量
                problem.setSubmit_pass_times(problem.getSubmit_pass_times() + 1);

                //判断是不是第一次答对这题，如果不是则不算进去
                int countOfPass = this.submissionMapper.isFirstPass(submission1);
                if (countOfPass == 1) {
                    problem.setSubmit_pass_number(problem.getSubmit_pass_number() + 1);
                    //新增一条积分记录
                    PointRecord pointRecord = new PointRecord();
                    pointRecord.setPoint(problem.getPoint());
                    pointRecord.setCreate_by(submission1.getUser_id());
                    pointRecord.setUser_id(submission1.getUser_id());
                    int flag3 = this.problemsMapper.insertPointRecord(pointRecord);

                    //修改用户M币值
                    User user = new User();
                    user.setId(submission1.getUser_id());
                    int flag4 = this.problemsMapper.updateUserPoint(user);
                    //如果有修改失败的，直接返回false
                    if (flag3 == 0 || flag4 == 0)
                        return null;
                }
            }
            int flag2 = this.problemsMapper.updateProblem(problem);
            if (flag2 == 0 || flag1 == 0)
                return null;
//            return this.submissionMapper.searchSubmissionIdByCreate_timeDESCAndUserId(submission.getUser_id());
            return submission1.getId();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("提交代码错误");
            return null;
        }
    }

    /**
     * 初始化时获取用户上次保存的代码
     *
     * @param submission
     * @return
     */
    @Cacheable(value = "submission", key = "'getCode-userId'+#submission.user_id+'-problemId-'+#submission.problem_id", unless = "#result == null")
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public Submission getCode(Submission submission) {
        //查询状态为0，也就是用户保存的代码，如果用户保存的，就返回代码，如果没有保存，就返回空串
        submission.setStatus(0);
        return this.submissionMapper.searchSubmissionByStatusAndUserIdAndProblemId(submission);
    }

}
