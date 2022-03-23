package com.mo.oj.service.impl;

import com.mo.oj.mapper.ProblemsMapper;
import com.mo.oj.pojo.Favorite;
import com.mo.oj.pojo.Problem;
import com.mo.oj.pojo.Submit;
import com.mo.oj.pojo.Tag;
import com.mo.oj.service.ProblemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

        System.out.println("submitList" + problemList);
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
}
