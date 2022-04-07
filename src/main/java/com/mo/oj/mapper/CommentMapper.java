package com.mo.oj.mapper;

import com.mo.oj.pojo.Comment;
import com.mo.oj.pojo.CommentGoodRecord;
import com.mo.oj.pojo.ProblemGoodRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {

    /**
     * 插入一条 点赞点踩记录
     *
     * @param commentGoodRecord
     * @return
     */
    @Insert("insert into comment_good_record(comment_id,user_id,number,create_time) " +
            "values(#{comment_id},#{user_id},#{number},now())")
    public Integer insertCommentGoodRecord(CommentGoodRecord commentGoodRecord);

    /**
     * 查询一条点赞或点踩记录
     *
     * @param user_id
     * @param comment_id
     * @return
     */
    @Select("select * from comment_good_record where user_id=#{user_id} and comment_id=#{comment_id}")
    public CommentGoodRecord searchCommentGoodRecord(Integer user_id, Integer comment_id);

    /**
     * 删除一条点赞点踩信息   条件comment_id、user_id
     *
     * @param commentGoodRecord
     * @return
     */
    @Delete("delete from comment_good_record where comment_id=#{comment_id} and user_id=#{user_id}")
    public Integer deleteCommentGoodRecord(CommentGoodRecord commentGoodRecord);

    /**
     * 删除多条点赞点踩信息   条件comment_id
     *
     * @param comment_id
     * @return
     */
    @Delete("delete from comment_good_record where comment_id=#{comment_id}")
    public Integer deleteManyCommentGoodRecord(Integer comment_id);

    /**
     * 插入一条评论
     *
     * @param comment
     * @return
     */
    public Integer insertComment(Comment comment);

    /**
     * 删除一条评论
     *
     * @param id
     * @return
     */
    @Delete("delete from comment where id=#{id}")
    public Integer deleteComment(Integer id);

    /**
     * 修改评论中的点赞点踩数量
     *
     * @param comment_id
     * @return
     */
    public Integer updateCommentNumber(Integer comment_id);

    /**
     * 查找comment集合，条件problem_id，level，
     * problem和level=1是查出当前题目下的所有一级评论
     * problem、level=2、first_comment_id=一级评论的id，是查出一级评论下的所有2、3级评论
     *
     * @return
     */
    public List<Comment> searchCommentListByComment(Comment comment);

    /**
     * 查询当前题目下的评论数量    ，条件problem_id
     *
     * @param problem_id
     * @return
     */
    @Select("select count(1) from comment where problem_id=#{problem_id}")
    public Integer searchCommentCountByProblemId(Integer problem_id);

    /**
     * 查询最近的评论list，
     *
     * @param user_id
     * @param start
     * @return
     */
    public List<Comment> searchCommentListRecently(Integer user_id, Integer start);

    /**
     * 查询评论的数量
     *
     * @param user_id
     * @return
     */
    public Integer searchCommentCountRecently(Integer user_id);

    /**
     * 查询一条评论数据，条件id
     *
     * @param id
     * @return
     */
    public Comment searchCommentOneById(Integer id);

}
