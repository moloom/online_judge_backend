package com.mo.oj.service;

import com.mo.oj.pojo.*;

import java.util.HashMap;
import java.util.List;

public interface ProblemsService {

    /**
     * 查询所有的标签
     *
     * @return
     */
    public List<Tag> searchTagAll();

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
    public List<Problem> searchProblemListByConditions(Integer difficulty, Integer status, Integer tag, String keyword, Integer user_id, Integer start);

    /**
     * 查询一条problem信息，条件id
     *
     * @param id
     * @return
     */
    public Problem searchProblemById(Integer id);

    /**
     * 判断当前用户所选的题目是否被收藏
     *
     * @param userId
     * @param problemId
     * @return
     */
    public Boolean isFavorite(Integer userId, Integer problemId);

    /**
     * 查询当前题目的点赞或点踩信息
     *
     * @param goodRecord
     * @return
     */
    public HashMap<String, Object> isGood(GoodRecord goodRecord);

    /**
     * 收藏或者取消收藏
     *
     * @param favorite
     * @param isFavorite
     * @return
     */
    public Boolean updateFavorite(Favorite favorite, boolean isFavorite);

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
    public Boolean updateGoodAndBad(GoodRecord goodRecord);

    /**
     * 保存代码
     *
     * @param submit
     * @return
     */
    public Boolean saveCode(Submit submit);

    /**
     * 初始化时获取用户上次保存的代码
     *
     * @param submit
     * @return
     */
    public Submit getCode(Submit submit);

}
