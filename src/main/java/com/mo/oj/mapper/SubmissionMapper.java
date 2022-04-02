package com.mo.oj.mapper;

import com.mo.oj.pojo.Submission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface SubmissionMapper {

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

    /**
     * 查找一条submission记录，条件：status、user_id、problem_id
     *
     * @param submission
     * @return
     */
    @Select("select * from submission where status=#{status} and user_id=#{user_id} and problem_id=#{problem_id}")
    public Submission searchSubmissionByStatusAndUserIdAndProblemId(Submission submission);

    /**
     * 新增提交记录
     *
     * @param submission
     * @return
     */
    public Integer insertSubmission(Submission submission);

    /**
     * 修改提交记录
     *
     * @param submission
     * @return
     */
    public Integer updateSubmission(Submission submission);
}
