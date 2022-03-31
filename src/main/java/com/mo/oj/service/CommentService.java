package com.mo.oj.service;

import com.mo.oj.pojo.Comment;
import com.mo.oj.pojo.CommentGoodRecord;

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
}
