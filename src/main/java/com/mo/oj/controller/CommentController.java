package com.mo.oj.controller;

import com.mo.oj.pojo.Comment;
import com.mo.oj.pojo.CommentGoodRecord;
import com.mo.oj.service.CommentService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


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

    @PostMapping("/insertComment")
    public Boolean insertComment(Comment comment) {
        System.out.println(comment);
        return this.commentService.insertComment(comment);
    }
}
