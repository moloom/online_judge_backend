package com.mo.oj.controller;

import com.mo.oj.pojo.Announcement;
import com.mo.oj.pojo.Comment;
import com.mo.oj.pojo.Problem;
import com.mo.oj.pojo.User;
import com.mo.oj.service.RankingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: mo
 * @date: 2022/4/8 下午4:57
 * @description:
 */
@RestController
@RequestMapping("/rank")
public class RankingController {

    @Resource
    RankingService rankingService;

    /**
     * 查询积分排行榜
     *
     * @return
     */
    @PostMapping("/pointRank")
    public List<User> pointRank(Integer start, Integer number) {
        return this.rankingService.pointRank(start, number);
    }

    /**
     * 查询公告list
     *
     * @return
     */
    @PostMapping("/searchAnnouncement")
    public List<Announcement> searchAnnouncement() {
        return this.rankingService.searchAnnouncement();
    }

    /**
     * 查询一条公告信息
     *
     * @return
     */
    @PostMapping("/searchAnnouncementOne")
    public Announcement searchAnnouncementOne(Integer id) {
        return this.rankingService.searchAnnouncementOne(id);
    }

    /**
     * 查询提交次数排行榜
     *
     * @param start
     * @return
     */
    @PostMapping("/searchSubmitTimesRank")
    public List<Problem> searchSubmitTimesRank(Integer start) {
        return this.rankingService.searchSubmitTimesRank(start);
    }

    /**
     * 查询提交人数排行榜
     *
     * @param start
     * @return
     */
    @PostMapping("/searchSubmitNumberRank")
    public List<Problem> searchSubmitNumberRank(Integer start) {
        return this.rankingService.searchSubmitNumberRank(start);
    }

    /**
     * 查询提交次数通过率排行榜
     *
     * @param start
     * @return
     */
    @PostMapping("/searchTimesPassRateRank")
    public List<Problem> searchTimesPassRateRank(Integer start) {
        return this.rankingService.searchTimesPassRateRank(start);
    }
}
