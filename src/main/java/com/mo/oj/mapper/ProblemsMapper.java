package com.mo.oj.mapper;

import com.mo.oj.pojo.Problem;
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
    public List<Problem> serachProblemListByConditions(Problem problem);
}
