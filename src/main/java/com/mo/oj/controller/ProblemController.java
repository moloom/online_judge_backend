package com.mo.oj.controller;

import com.mo.oj.pojo.*;
import com.mo.oj.service.ProblemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.crypto.interfaces.PBEKey;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/problems")
public class ProblemController {

    @Resource
    ProblemsService problemsService;

    @PostMapping("/searchTagAll")
    public List<Tag> searchTagAll() {
        return this.problemsService.searchTagAll();
    }

    @PostMapping("/searchProblemListByConditions")
    public List<Problem> searchProblemListByConditions(Integer difficulty, Integer status, Integer tag, String keyword, Integer user_id, Integer start) {
        if (start == null) start = 0;
        return this.problemsService.searchProblemListByConditions(difficulty, status, tag, keyword, user_id, start);
    }

    @PostMapping("/searchProblemById")
    public Problem searchProblemById(Integer id) {
        return this.problemsService.searchProblemById(id);
    }

    @PostMapping("/isFavorite")
    public Boolean isFavorite(Integer userId, Integer problemId) {
        return this.problemsService.isFavorite(userId, problemId);
    }

    @PostMapping("/isGood")
    public HashMap<String, Object> isGood(GoodRecord goodRecord) {
        //查询：题目是否点赞或点踩
        return this.problemsService.isGood(goodRecord);
    }

    @PostMapping("/updateFavorite")
    public Boolean updateFavorite(Favorite favorite, boolean isFavorite) {
        return this.problemsService.updateFavorite(favorite, isFavorite);
    }

    /**
     * 修改点赞点踩的信息
     *
     * @param goodRecord
     * @return
     */
    @PostMapping("/updateGoodAndBad")
    public Boolean updateGoodAndBad(GoodRecord goodRecord) {
        return this.problemsService.updateGoodAndBad(goodRecord);
    }

    /**
     * 保存代码
     *
     * @param submit
     * @return
     */
    @PostMapping("/saveCode")
    public Boolean saveCode(Submit submit) {
        return this.problemsService.saveCode(submit);
    }

    /**
     * 初始化时，获取用户上次保存的代码
     *
     * @param submit
     * @return
     */
    @PostMapping("/getCode")
    public Submit getCode(Submit submit) {
        return this.problemsService.getCode(submit);
    }
}
