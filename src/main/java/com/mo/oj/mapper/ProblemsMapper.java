package com.mo.oj.mapper;

import com.mo.oj.pojo.Favorite;
import com.mo.oj.pojo.Problem;
import com.mo.oj.pojo.Submit;
import com.mo.oj.pojo.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
    public List<Problem> searchProblemListByCondition(Integer difficulty, Integer status, Integer tag, String keyword, Integer user_id, Integer start);

    /**
     * 查询一条problem信息，条件id
     *
     * @param id
     * @return
     */
    @Select("select * from problem p where id=#{id}")
    public Problem searchProblemById(Integer id);

    /**
     * 查询favorite记录条数，条件userId，problemId
     *
     * @param userId
     * @param problemId
     * @return
     */
    @Select("select count(1) from favorite where problem_id=#{problemId} and create_by = #{userId}")
    public Integer searchFavoriteCountByUserIdAndProblemId(Integer userId, Integer problemId);

    /**
     * 取消收藏
     *
     * @param favorite
     * @return
     */
    @Delete("delete from favorite where problem_id=#{problem_id} and create_by=#{create_by}")
    public Integer deleteFavorite(Favorite favorite);

    /**
     * 添加收藏
     *
     * @param favorite
     * @return
     */
    @Insert("insert into favorite(problem_id,create_time,create_by) values(#{problem_id},now(),#{create_by})")
    public Integer addFavorite(Favorite favorite);
}
