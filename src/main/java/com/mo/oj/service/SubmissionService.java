package com.mo.oj.service;

import com.mo.oj.pojo.Submission;

import java.util.HashMap;
import java.util.List;

public interface SubmissionService {

    /**
     * 查询提交记录list，条件map
     *
     * @return
     */
    public List<Submission> searchSubmissionListByMap(Submission submission);

    /**
     * 查询提交记录条数
     *
     * @param submission
     * @return
     */
    public Integer searchSubmissionCountBySubmission(Submission submission);

    /**
     * 查询一条提交记录
     *
     * @param id
     * @return
     */
    public Submission searchOneSubmissionById(Integer id);
}
