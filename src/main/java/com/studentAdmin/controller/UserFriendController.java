package com.studentAdmin.controller;

import com.common.QueryParam;
import com.common.Result;
import com.studentAdmin.domain.User;
import com.studentAdmin.domain.UserFriend;
import com.studentAdmin.domain.VOs.FriendRequestVO;
import com.studentAdmin.domain.common.Common;
import com.studentAdmin.domain.common.CommonException;
import com.studentAdmin.service.FriendRequestService;
import com.studentAdmin.service.UserFriendService;
import com.studentAdmin.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-5  21:13
 * @version: 1.0
 */
@Controller
public class UserFriendController {

    @Autowired
    UserFriendService userFriendService;

    @Autowired
    FriendRequestService friendRequestService;

    /**
     * 删除好友
     *
     * @param id
     * @return
     */
    @RequestMapping("user/deleteFriend.do")
    @ResponseBody
    public Result deleteFriend(@RequestParam("id") long id) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        try {
            userFriendService.deleteFriendShip(id);
            return Result.ok();
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * 同意加为好友
     *
     * @param id
     * @return
     */
    @RequestMapping("user/agreeBeFriend.do")
    @ResponseBody
    public Result agreeBeFriend(@RequestParam("id") long id, @RequestParam("friendId") long friendId) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        try {
            userFriendService.beFriendShip(id, user.getUserId(), friendId);
            return Result.ok();
        } catch (CommonException e) {
            return Result.error(e.getCode(), e.getMsg());
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * 发送好友请求
     *
     * @param friendId
     * @return
     */
    @RequestMapping("user/sendFriendRequest.do")
    @ResponseBody
    public Result sendFriendRequest(@RequestParam("friendId") long friendId) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        try {
            friendRequestService.sendRequest(user.getUserId(), friendId);
            return Result.ok();
        } catch (CommonException e) {
            return Result.error(e.getCode(), e.getMsg());
        }
    }

    /**
     * 拒绝好友请求
     *
     * @param id
     * @return
     */
    @RequestMapping("user/rejectBeFriend.do")
    @ResponseBody
    public Result rejectBeFriend(@RequestParam("id") long id) {
        try {
            friendRequestService.rejectRequest(id);
            return Result.ok();
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * 获取好友请求
     *
     * @param showFlag
     * @return
     */
    @RequestMapping("user/qryRequest.do")
    @ResponseBody
    public Result qryRequest(@RequestParam("showFlag") int showFlag) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserId());
        map.put("showFlag", showFlag);
        List<FriendRequestVO> friendRequestVOS = friendRequestService.qryRequest(map);
        return Result.ok().put("list", friendRequestVOS);
    }

    /**
     * 获取好友列表
     * @param friendName
     * @return
     */
    @RequestMapping("user/queryFriend.do")
    @ResponseBody
    public Result queryFriend(@RequestParam("friendName") String friendName) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) SessionUtil.getSessionAttribute("user");
        map.put("userId", user.getUserId());
        map.put("friendName", friendName);
        List<UserFriend> userFriends = userFriendService.qryUserFriend(map);
        return Result.ok().put("list", userFriends);

    }
}
