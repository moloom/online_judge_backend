package com.mo.oj.service;

import com.mo.oj.pojo.Comment;
import com.mo.oj.pojo.CommentGoodRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface CommentService {

    /**
     * 修改点赞点踩记录
     *
     * @param commentGoodRecord
     * @return
     */
    public Boolean changeGoodAndBad(CommentGoodRecord commentGoodRecord);

    /**
     * 插入一条评论
     *
     * @param comment
     * @return
     */
    public Boolean insertComment(Comment comment);

    /**
     * 删除一条评论
     *
     * @param id
     * @return
     */
    public Boolean deleteComment(Integer id, Integer problem_id);

    /**
     * 查找comment集合，条件problem_id
     * 暂时 先不分页，直接查所有
     *
     * @return
     */
    public List<Comment> searchCommentListByProblemId(Integer problem_id);

    /**
     * 查找用户对当前题目中的评论的点赞点踩信息，条件problem_id , user_id
     *
     * @param comment
     * @return
     */
    public HashMap<Object, ArrayList> searchGoodAndBadInfo(Comment comment);

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

    /**
     * 查询用户对一条评论的的点赞点踩的谢谢，条件user_id , comment_id
     *
     * @param user_id
     * @param comment_id
     * @return
     */
    public HashMap<String, Object> searchCommentOneGoodAndBad(Integer user_id, Integer comment_id);

}
