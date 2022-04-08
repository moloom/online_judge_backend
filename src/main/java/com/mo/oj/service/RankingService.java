package com.mo.oj.service;

import com.mo.oj.pojo.Announcement;
import com.mo.oj.pojo.Comment;
import com.mo.oj.pojo.User;

import java.util.List;

/**
 * @author: mo
 * @date: 2022/4/8 下午5:21
 * @description:
 */
public interface RankingService {

    /**
     * 查询积分排行榜
     *
     * @return
     */
    public List<User> pointRank(Integer start, Integer number);

    /**
     * 查询点赞最多9条的评论
     *
     * @return
     */
    public List<Announcement> searchAnnouncement();

    /**
     * 查询一条公告信息
     *
     * @return
     */
    public Announcement searchAnnouncementOne(Integer id);
}
