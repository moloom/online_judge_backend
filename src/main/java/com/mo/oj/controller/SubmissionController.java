package com.mo.oj.controller;

import com.mo.oj.pojo.Submission;
import com.mo.oj.service.SubmissionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RequestMapping("submission")
@RestController
public class SubmissionController {

    @Resource
    SubmissionService submissionService;

    /**
     * 查询提交记录，条件problem_id
     *
     * @param submission
     * @return
     */
    @PostMapping("/searchSubmissionList")
    public List<Submission> searchSubmissionListByMap(Submission submission) {
        return this.submissionService.searchSubmissionListByMap(submission);
    }

    /**
     * 查询提交记录条数
     *
     * @param submission
     * @return
     */
    @PostMapping("/searchSubmissionCount")
    public Integer searchSubmissionCountBySubmission(Submission submission) {
        return this.submissionService.searchSubmissionCountBySubmission(submission);
    }

    /**
     * 查询一条提交记录
     *
     * @param id
     * @return
     */
    @PostMapping("/searchOneSubmission")
    public Submission searchOneSubmissionById(Integer id) {
        return this.submissionService.searchOneSubmissionById(id);
    }
}
