package com.mo.oj.controller;

import com.mo.oj.pojo.*;
import com.mo.oj.service.ProblemsService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    @Resource
    ProblemsService problemsService;

    @PostMapping("/searchTagAll")
    public List<Tag> searchTagAll() {
        return this.problemsService.searchTagAll();
    }

    @PostMapping("/searchTagCloudList")
    public List<Tag> searchTagCloudList() {
        return this.problemsService.searchTagCloudList();
    }

    @PostMapping("/searchProblemListByConditions")
    public List<Problem> searchProblemListByConditions(Integer difficulty, Integer status, Integer tag, String keyword, Integer user_id, Integer start) {
        if (start == null) start = 0;
        return this.problemsService.searchProblemListByConditions(difficulty, status, tag, keyword, user_id, start);
    }

    @PostMapping("/searchProblemCountByConditions")
    public Integer searchProblemCountByConditions(Integer difficulty, Integer status, Integer tag, String keyword, Integer user_id) {
        return this.problemsService.searchProblemCountByCondition(difficulty, status, tag, keyword, user_id);
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
    public HashMap<String, Object> isGood(ProblemGoodRecord problemGoodRecord) {
        //查询：题目是否点赞或点踩
        return this.problemsService.isGood(problemGoodRecord);
    }

    @PostMapping("/updateFavorite")
    public Boolean updateFavorite(Favorite favorite, boolean isFavorite) {
        return this.problemsService.updateFavorite(favorite, isFavorite);
    }

    /**
     * 修改点赞点踩的信息
     *
     * @param problemGoodRecord
     * @return
     */
    @PostMapping("/changeGoodAndBad")
    public Boolean changeGoodAndBad(ProblemGoodRecord problemGoodRecord) {
        return this.problemsService.updateGoodAndBad(problemGoodRecord);
    }

    /**
     * 保存代码
     *
     * @param submission
     * @return
     */
    @PostMapping("/saveCode")
    public Boolean saveCode(Submission submission) {
        return this.problemsService.saveCode(submission);
    }

    /**
     * 用户提交代码
     *
     * @param submission
     * @return
     */
    @PostMapping("/submitCode")
//    @Async
    public Integer submitCode(Submission submission) {
        return this.problemsService.submitCode(submission);
    }

    /**
     * 初始化时，获取用户上次保存的代码
     *
     * @param submission
     * @return
     */
    @PostMapping("/getCode")
    public Submission getCode(Submission submission) {
        return this.problemsService.getCode(submission);
    }

}
