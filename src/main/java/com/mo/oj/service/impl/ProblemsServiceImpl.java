package com.mo.oj.service.impl;

import com.mo.oj.mapper.ProblemsMapper;
import com.mo.oj.mapper.SubmissionMapper;
import com.mo.oj.pojo.*;
import com.mo.oj.service.ProblemsService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class ProblemsServiceImpl implements ProblemsService {

    @Resource
    ProblemsMapper problemsMapper;

    @Resource
    SubmissionMapper submissionMapper;

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
            tagList.get(i).setValue(""+(i+10));
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
