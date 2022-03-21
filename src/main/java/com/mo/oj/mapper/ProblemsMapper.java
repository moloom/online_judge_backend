package com.mo.oj.mapper;

import com.mo.oj.pojo.Problem;
import com.mo.oj.pojo.Submit;
import com.mo.oj.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProblemsMapper {

    /**
     * 查询所有的tag
     *
     * @return
     */
    @Select("select * from tag")
    public List<Tag> searchTagAll();

    /**
     * 查询题目list，条件：difficulty、state、tag、title or id
     *
     * @param problem
     * @return
     */
    public List<Problem> searchProblemListByConditions(Problem problem);

    /**
     * 查询用户当前已解答的题目list，条件：user_id，status
     *
     * @param user_id
     * @param status
     * @return
     */
    public List<Problem> searchProblemListByCondition(Integer difficulty, Integer status, Integer tag, String keyword, Integer user_id,Integer start);
}
