package com.studentAdmin.controller;

import com.common.QueryParam;
import com.common.Result;
import com.common.ResultPage;
import com.studentAdmin.dao.mapper.ArticleMapper;
import com.studentAdmin.domain.Article;
import com.studentAdmin.domain.Dto.RecommendDto;
import com.studentAdmin.domain.FriendRecommend;
import com.studentAdmin.domain.User;
import com.studentAdmin.domain.VOs.ArticleVO;
import com.studentAdmin.domain.VOs.FriendRecommendVO;
import com.studentAdmin.domain.VOs.HotArticleVO;
import com.studentAdmin.service.FriendRecommendService;
import com.studentAdmin.util.RecommendUtil;
import com.studentAdmin.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-18  16:26
 * @version: 1.0
 */
@Controller
public class RecommendController {

    @Autowired
    FriendRecommendService friendRecommendService;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    RecommendUtil recommendUtil;

    @RequestMapping("user/recommendFriend.do")
    @ResponseBody
    public Result recommendForFriend(@RequestBody List<RecommendDto> recommendDtos) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        List<FriendRecommend> list = new ArrayList<>();
        for (RecommendDto recommendDto : recommendDtos) {
            FriendRecommend friendRecommend = new FriendRecommend();
            friendRecommend.setArticleId(recommendDto.getArticleId());
            friendRecommend.setFriendId(user.getUserId());
            friendRecommend.setUserId(recommendDto.getUserId());
            list.add(friendRecommend);
        }
        try {
            friendRecommendService.insertRecommend(list);
            return Result.ok();
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * 系统推荐
     *
     * @return
     */
    @RequestMapping("user/sysRecommend.do")
    @ResponseBody
    public Result sysRecommend() {
        User user = (User) SessionUtil.getSessionAttribute("user");
        List<Long> articleIds = recommendUtil.doSystemRecommend(user.getUserId());
        List<ArticleVO> list= null;
        if(articleIds.size()!=0){
            list = articleMapper.getArticleByArticleIDs(articleIds);

        }
        return Result.ok().put("list", list);

    }

    /**
     * 获取好友推荐
     *
     * @return
     */
    @RequestMapping("user/friendRecommend.do")
    @ResponseBody
    public Result friendRecommend(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("limit", limit);
        map.put("userId", user.getUserId());
        QueryParam param = new QueryParam(map);
        ResultPage resultPage = friendRecommendService.getFriendRecommends(param);
        return Result.ok().put("page", resultPage);

    }

    /**
     * 获取热门文章
     *
     * @return
     */
    @RequestMapping("user/getHotArticle.do")
    @ResponseBody
    public Result getHotArticle() {
        List<HotArticleVO> hotArticles = articleMapper.getHotArticle();
        return  Result.ok().put("list",hotArticles);
    }

}
