package com.mo.oj.controller;

import com.mo.oj.pojo.Problem;
import com.mo.oj.pojo.Tag;
import com.mo.oj.service.ProblemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
        System.out.println("searchProblemListByConditions=" + "\n" + difficulty + "\n" + status + "\n" + tag + "\n" + keyword + "\n" + user_id + "\n" + start);
        if (start == null)
            start = 0;
        return this.problemsService.searchProblemListByConditions(difficulty, status, tag, keyword, user_id, start);
    }//不能清楚条件，点击尝试过会报错，
}
