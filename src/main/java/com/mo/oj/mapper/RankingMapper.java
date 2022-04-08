package com.mo.oj.mapper;

import com.mo.oj.pojo.Announcement;
import com.mo.oj.pojo.Comment;
import com.mo.oj.pojo.Problem;
import com.mo.oj.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: mo
 * @date: 2022/4/8 下午5:03
 * @description:
 */
@Mapper
@Repository
public interface RankingMapper {

    /**
     * 查询积分排行榜
     *
     * @return
     */
    public List<User> pointRank(Integer start, Integer number);

    /**
     * 查询9条最近公告
     *
     * @return
     */
    public List<Announcement> searchAnnouncement();

    /**
     * 查询一条公告信息
     *
     * @param id
     * @return
     */
    public Announcement searchAnnouncementOne(Integer id);

    /**
     * 查询提交次数排行榜
     *
     * @param start
     * @return
     */
    public List<Problem> searchSubmitTimesRank(Integer start);

    /**
     * 查询提交人数排行榜
     *
     * @param start
     * @return
     */
    public List<Problem> searchSubmitNumberRank(Integer start);

    /**
     * 查询提交次数通过率排行榜
     *
     * @param start
     * @return
     */
    public List<Problem> searchTimesPassRateRank(Integer start);
}
