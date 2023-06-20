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
     * 查找用户最近一条提交记录的id
     *
     * @return
     */
    @Select("select id from submission where user_id=#{user_id} order by create_time DESC limit 0,1")
    public Integer searchSubmissionIdByCreate_timeDESCAndUserId(Integer user_id);


    /**
     * 查询是否第一次提交这道题的代码，条件problem_id,user_id
     *
     * @param submission
     * @return
     */
    @Select("select count(*) from submission where user_id=#{user_id} and problem_id=#{problem_id} and status!=0")
    public Integer isFirstTimes(Submission submission);

    /**
     * 查询是否第一次提交这道题的代码，条件problem_id,user_id
     *
     * @param submission
     * @return
     */
    @Select("select count(1) from submission where user_id=#{user_id} and problem_id=#{problem_id} and status=1")
    public Integer isFirstPass(Submission submission);

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
