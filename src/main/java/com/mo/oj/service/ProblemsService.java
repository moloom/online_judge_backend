package com.mo.oj.service;

import com.mo.oj.pojo.Favorite;
import com.mo.oj.pojo.Problem;
import com.mo.oj.pojo.Tag;

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
     * 收藏或者取消收藏
     *
     * @param favorite
     * @param isFavorite
     * @return
     */
    public Boolean updateFavorite(Favorite favorite, boolean isFavorite);

}
