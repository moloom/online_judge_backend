package com.mo.oj.service.impl;

import com.mo.oj.mapper.CommentMapper;
import com.mo.oj.pojo.Comment;
import com.mo.oj.pojo.CommentGoodRecord;
import com.mo.oj.service.CommentService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentMapper commentMapper;

    /**
     * 修改点赞点踩记录
     *
     * @param commentGoodRecord
     * @return
     */
//    @CacheEvict(value = "comment", key = "'searchCommentByid'+#commentGoodRecord.")
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, timeout = 15)
    @Override
    public Boolean changeGoodAndBad(CommentGoodRecord commentGoodRecord) {

        return null;
    }

    /**
     * 插入一条评论
     *
     * @param comment
     * @return
     */
    @Override
    public Boolean insertComment(Comment comment) {
        comment.setGood(0);
        comment.setBad(0);
        int flag = this.commentMapper.insertComment(comment);
        if (flag != 1)
            return false;
        return true;
    }
}
