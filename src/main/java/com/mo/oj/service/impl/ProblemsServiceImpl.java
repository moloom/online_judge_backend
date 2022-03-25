package com.mo.oj.service.impl;

import com.mo.oj.mapper.ProblemsMapper;
import com.mo.oj.pojo.*;
import com.mo.oj.service.ProblemsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 查询所有的标签
     *
     * @return
     */
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public List<Tag> searchTagAll() {
        return this.problemsMapper.searchTagAll();
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
     * 查询一条problem信息，条件id
     *
     * @param id
     * @return
     */
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
     * @param goodRecord
     * @return
     */
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public HashMap<String, Object> isGood(GoodRecord goodRecord) {
        //查询good_record表，看有没有点赞点踩的记录，
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("isGood", false);
        map.put("isBad", false);
        GoodRecord findGoodRecord = this.problemsMapper.isGood(goodRecord);
        if (findGoodRecord == null)
            return map;
        if (findGoodRecord.getNumber() > 0)
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
     *
     * @param goodRecord
     * @return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, timeout = 20)
    @Override
    public Boolean updateGoodAndBad(GoodRecord goodRecord) {
        int flag = flag = this.problemsMapper.deleteGoodRecord(goodRecord);
        int flag1 = 0;
        if (goodRecord.getNumber() == 1) {
            flag1 = this.problemsMapper.insertGoodRecord(goodRecord);
        } else if (goodRecord.getNumber() == -1) {
            flag1 = this.problemsMapper.insertGoodRecord(goodRecord);
        }
        //修改problem中的good、bad数量
        int flag2 = this.problemsMapper.updateProblemGoodAndBadNumber(goodRecord.getProblem_id());
        if (goodRecord.getNumber() == 0 && flag > 0 && flag2 > 0)
            return true;
        else if (goodRecord.getNumber() != 0 && flag1 > 0 && flag2 > 0)
            return true;
        return false;
    }

    /**
     * 保存代码
     *
     * @param submit
     * @return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, timeout = 15)
    @Override
    public Boolean saveCode(Submit submit) {
        //如果提交记录中没有状态为0的记录，则新增一条状态为0的记录，如果有则修改就行
        submit.setStatus(0);
        int flag = 0;
        Submit searchSubmit = this.problemsMapper.searchSubmitByStatusAndUserIdAndProblemId(submit);
        submit.setId(searchSubmit.getId());
        if (searchSubmit == null)
            flag = this.problemsMapper.insertSubmit(submit);
        else flag = this.problemsMapper.updateSubmit(submit);
        if (flag > 0)
            return true;
        return false;
    }

    /**
     * 初始化时获取用户上次保存的代码
     *
     * @param submit
     * @return
     */
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public Submit getCode(Submit submit) {
        //查询状态为0，也就是用户保存的代码，如果用户保存的，就返回代码，如果没有保存，就返回空串
        submit.setStatus(0);
        return this.problemsMapper.searchSubmitByStatusAndUserIdAndProblemId(submit);
    }
}
