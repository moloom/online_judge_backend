package com.mo.oj.mapper;

import com.mo.oj.pojo.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProblemsMapper {


    /*--------problem---------------------------------------------------------*/

    /**
     * 查询题目list，条件：difficulty、state、tag、title or id
     *
     * @param problem
     * @return
     */
//    public List<Problem> searchProblemListByConditions(Problem problem);

    /**
     * 查询题目list，条件：user_id，status,difficulty,tag,keyword,user_id,start
     *
     * @param user_id
     * @param status
     * @return
     */
    public List<Problem> searchProblemListByCondition(Integer difficulty, Integer status, Integer tag, String keyword, Integer user_id, Integer start);

    /**
     * 查询题目的数量，条件：user_id，status,difficulty,tag,keyword,user_id
     *
     * @param user_id
     * @param status
     * @return
     */
    public Integer searchProblemCountByCondition(Integer difficulty, Integer status, Integer tag, String keyword, Integer user_id);

    /**
     * 查询一条problem信息，条件id
     *
     * @param id
     * @return
     */
    @Select("select * from problem p where id=#{id}")
    public Problem searchProblemById(Integer id);

    /**
     * 修改problem信息
     *
     * @param problem
     * @return
     */
    public Integer updateProblem(Problem problem);//未完成

    /**
     * 修改good、bad的个数
     *
     * @param problem_id
     * @return
     */
    public Integer updateProblemGoodAndBadNumber(Integer problem_id);

    /**
     * 查询所有的tag
     *
     * @return
     */
    @Select("select * from tag")
    public List<Tag> searchTagAll();

    /*---------favorite--------------------------------------------------------*/

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

    /*-----------problemGoodRecord------------------------------------------------------*/

    /**
     * 查询当前题目的点赞或点踩信息
     *
     * @param problemGoodRecord
     * @return
     */
    @Select("select * from problem_good_record where user_id=#{user_id} and problem_id=#{problem_id}")
    public ProblemGoodRecord isGood(ProblemGoodRecord problemGoodRecord);

    /**
     * 添加一条点赞点踩信息
     *
     * @param problemGoodRecord
     * @return
     */
    @Insert("insert into problem_good_record(problem_id,user_id,number,create_time) values(#{problem_id},#{user_id},#{number},now())")
    public Integer insertProblemGoodRecord(ProblemGoodRecord problemGoodRecord);

    /**
     * 删除一条点赞点踩信息
     *
     * @param problemGoodRecord
     * @return
     */
    @Delete("delete from problem_good_record where problem_id=#{problem_id} and user_id=#{user_id}")
    public Integer deleteProblemGoodRecord(ProblemGoodRecord problemGoodRecord);


    /*---------language--------------------------------------------------------*/


//    /**
//     * 查找一条language记录，条件：name
//     *
//     * @param name
//     * @return
//     */
//    @Select("select * from language where name=#{name}")
//    public Language searchLanguageByName(String name);
}
