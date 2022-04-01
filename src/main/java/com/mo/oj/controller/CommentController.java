package com.mo.oj.controller;

import com.mo.oj.pojo.Comment;
import com.mo.oj.pojo.CommentGoodRecord;
import com.mo.oj.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RequestMapping("/comment")
@RestController
public class CommentController {

    @Resource
    CommentService commentService;

    /**
     * 修改点赞点踩记录
     *
     * @param commentGoodRecord
     * @return
     */
    @PostMapping("/changeGoodAndBad")
    public Boolean changeGoodAndBad(CommentGoodRecord commentGoodRecord) {
        return this.commentService.changeGoodAndBad(commentGoodRecord);
    }

    /**
     * 插入一条评论
     *
     * @param comment
     * @return
     */
    @PostMapping("/insertComment")
    public Boolean insertComment(Comment comment) {
        System.out.println(comment);
        return this.commentService.insertComment(comment);
    }

    /**
     * 删除一条评论
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteComment")
    public Boolean deleteComment(Integer id, Integer problem_id) {
        return this.commentService.deleteComment(id, problem_id);
    }

    /**
     * 查找comment集合，条件problem_id
     * 暂时 先不分页，直接查所有
     *
     * @return
     */
    @PostMapping("/searchCommentListByProblemId")
    public List<Comment> searchCommentListByProblemId(Integer problem_id) {
        return this.commentService.searchCommentListByProblemId(problem_id);
    }

    /**
     * 查找用户对当前题目中的评论的点赞点踩信息，条件problem_id , user_id
     *
     * @param comment
     * @return
     */
    @PostMapping("/searchGoodAndBadInfo")
    public HashMap<Object, ArrayList> searchGoodAndBadInfo(Comment comment) {
        return this.commentService.searchGoodAndBadInfo(comment);
    }
}
