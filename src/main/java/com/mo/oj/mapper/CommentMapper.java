package com.mo.oj.mapper;

import com.mo.oj.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommentMapper {

    /**
     * 插入一条评论
     *
     * @param comment
     * @return
     */
    public Integer insertComment(Comment comment);
}
