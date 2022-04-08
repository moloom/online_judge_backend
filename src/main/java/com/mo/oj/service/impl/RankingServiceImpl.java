package com.mo.oj.service.impl;

import com.mo.oj.mapper.RankingMapper;
import com.mo.oj.pojo.Announcement;
import com.mo.oj.pojo.Comment;
import com.mo.oj.pojo.User;
import com.mo.oj.service.RankingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: mo
 * @date: 2022/4/8 下午5:22
 * @description:
 */
@Service
public class RankingServiceImpl implements RankingService {

    @Resource
    RankingMapper rankingMapper;

    /**
     * 查询积分排行榜
     *
     * @return
     */
    @Transactional(readOnly = true, timeout = 20)
    @Override
    public List<User> pointRank(Integer start, Integer number) {
        return this.rankingMapper.pointRank(start, number);
    }

    /**
     * 查询点赞最多9条的评论
     *
     * @return
     */
    @Transactional(readOnly = true, timeout = 20)
    @Override
    public List<Announcement> searchAnnouncement() {
        return this.rankingMapper.searchAnnouncement();
    }

    /**
     * 查询一条公告信息
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true, timeout = 20)
    @Override
    public Announcement searchAnnouncementOne(Integer id) {
        return this.rankingMapper.searchAnnouncementOne(id);
    }
}
