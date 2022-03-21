package com.mo.oj.controller;

import com.mo.oj.pojo.Problem;
import com.mo.oj.pojo.Tag;
import com.mo.oj.service.ProblemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/problems")
public class ProblemController {

    @Autowired
    ProblemsService problemsService;

    @PostMapping("/searchTagAll")
    public List<Tag> searchTagAll() {
        return this.problemsService.searchTagAll();
    }

    @PostMapping("/serachProblemListByConditions")
    public List<Problem> serachProblemListByConditions(Problem problem) {
        System.out.println("serachProblemListByConditions===\n" + problem);
        //少了用户id条件，需要用户id查询出状态这个字段。
        return null;
    }
}
