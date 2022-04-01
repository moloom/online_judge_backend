package com.mo.oj.service.impl;

import com.mo.oj.mapper.CommentMapper;
import com.mo.oj.pojo.Comment;
import com.mo.oj.pojo.CommentGoodRecord;
import com.mo.oj.service.CommentService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    @CacheEvict(value = "commentGoodRecord", key = "'commentGood-pid'+#commentGoodRecord.id+'-uid'+#commentGoodRecord.user_id")
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, timeout = 15)
    @Override
    public Boolean changeGoodAndBad(CommentGoodRecord commentGoodRecord) {
        //删除已有的点赞点踩记录
        int flag = flag = this.commentMapper.deleteCommentGoodRecord(commentGoodRecord);
        int flag1 = 0;
        //插入一条点赞点踩记录
        if (commentGoodRecord.getNumber() == 1 || commentGoodRecord.getNumber() == -1)
            flag1 = this.commentMapper.insertCommentGoodRecord(commentGoodRecord);
        //修改comment中的good、bad数量
        int flag2 = this.commentMapper.updateCommentNumber(commentGoodRecord.getComment_id());
        if (commentGoodRecord.getNumber() == 0 && flag > 0 && flag2 > 0)
            return true;
        else if (commentGoodRecord.getNumber() != 0 && flag1 > 0 && flag2 > 0)
            return true;
        return false;
    }


    /**
     * 查找用户对当前题目中的评论的点赞点踩信息，条件problem_id , user_id
     * 获取数据后添加到redis缓存
     *
     * @param comment
     * @return
     */
    @Cacheable(value = "commentGoodRecord", key = "'commentGood-pid'+#comment.problem_id+'-uid'+#comment.user_id")
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public HashMap<Object, ArrayList> searchGoodAndBadInfo(Comment comment) {
        HashMap<Object, ArrayList> map = new HashMap<Object, ArrayList>();
        //先查出题目下的评论
        comment.setLevel(2);//level为2可以躲避动态sql的条件，从而只根据problem_id一个条件来查
        List<Comment> commentList = this.commentMapper.searchCommentListByComment((comment));
        //遍历评论，逐条查出当前用户是否点赞或点踩
        for (Comment cm : commentList) {
            ArrayList arrayList = new ArrayList();
            CommentGoodRecord commentGoodRecord = this.commentMapper.searchCommentGoodRecord(comment.getUser_id(), cm.getId());
            //如果不为空，也就是有点赞点踩记录，则区分是否点赞或点踩，如果为空则设置默认值false
            if (commentGoodRecord != null) {
                //根据number字段来区分是否点赞或点踩，list第一个是good，第二个是bad
                if (commentGoodRecord.getNumber() > 0) {
                    arrayList.add(true);
                    arrayList.add(false);
                } else {
                    arrayList.add(false);
                    arrayList.add(true);
                }
            } else {
                arrayList.add(false);
                arrayList.add(false);
            }
            map.put(cm.getId(), arrayList);
        }
        return map;
    }

    /**
     * 插入一条评论 ,插入评论要删除评论的缓存
     *
     * @param comment
     * @return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, timeout = 15)
    @CacheEvict(value = "comment", key = "'searchCommentListByProblemId'+#comment.problem_id")
    @Override
    public Boolean insertComment(Comment comment) {
        //初始化点赞点踩数量。
        comment.setGood(0);
        comment.setBad(0);
        int flag = this.commentMapper.insertComment(comment);
        if (flag != 1)
            return false;
        return true;
    }

    /**
     * 删除一条评论，删除评论要删除评论的缓存
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, timeout = 15)
    @CacheEvict(value = "comment", key = "'searchCommentListByProblemId'+#problem_id")
    @Override
    public Boolean deleteComment(Integer id, Integer problem_id) {
        int flag = this.commentMapper.deleteComment(id);
        if (flag != 0)
            return true;
        return false;
    }

    /**
     * 查找comment集合，条件problem_id
     * 暂时 先不分页，直接查所有
     *
     * @param problem_id
     * @return
     */
    @Cacheable(value = "comment", key = "'searchCommentListByProblemId'+#problem_id")
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public List<Comment> searchCommentListByProblemId(Integer problem_id) {
        //查出当前题目下的所有一级评论
        Comment commentCondition = new Comment();
        commentCondition.setProblem_id(problem_id);
        commentCondition.setLevel(1);   //1 代表一级评论
        List<Comment> commentList = this.commentMapper.searchCommentListByComment(commentCondition);
        //对评论list判空，
        if (commentList != null && !commentList.isEmpty()) {
            //如果不为空则循环list，查出每条一级评论下的子评论
            for (int i = 0; i < commentList.size(); ++i) {
                Comment condition = new Comment();//用来查询的条件
                //查询一级评论下的子评论条件是：problem_id 和 first_comment_id等于当前一级评论id
                condition.setProblem_id(problem_id);
                condition.setFirst_comment_id(commentList.get(i).getId());
                //查询一级评论下的子评论，并把查询到的子评论赋给一级评论
                commentList.get(i).setCommentChildList(this.commentMapper.searchCommentListByComment(condition));
            }
        }
        //problem、level=2、first_comment_id=一级评论的id，是查出一级评论下的所有2、3级评论
        return commentList;
    }

}
