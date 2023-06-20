package com.mo.oj.service.impl;

import com.mo.oj.mapper.SubmissionMapper;
import com.mo.oj.pojo.Submission;
import com.mo.oj.service.SubmissionService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Resource
    SubmissionMapper submissionMapper;

    /**
     * 查询提交记录list，条件map
     *
     * @param submission
     * @return
     */
//    @Cacheable(value = "submission", key = "'getCode-userId'+#submission.user_id+'-problemId-'+#submission.problem_id")
//    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, timeout = 15)
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public List<Submission> searchSubmissionListByMap(Submission submission) {
        if (submission.getStart() == null)
            submission.setStart(0);
        return this.submissionMapper.searchSubmissionListByMap(submission);
    }

    /**
     * 查询提交记录条数
     *
     * @param submission
     * @return
     */
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public Integer searchSubmissionCountBySubmission(Submission submission) {
        return this.submissionMapper.searchSubmissionCountBySubmission(submission);
    }

    /**
     * 查询一条提交记录
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public Submission searchOneSubmissionById(Integer id) {
        return this.submissionMapper.searchOneSubmissionById(id);
    }

}
