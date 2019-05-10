package com.studentAdmin.controller;

import com.common.QueryParam;
import com.common.Result;
import com.common.ResultPage;
import com.studentAdmin.dao.mapper.ArticleCollectMapper;
import com.studentAdmin.domain.ArticleCollect;
import com.studentAdmin.domain.ArticleScore;
import com.studentAdmin.domain.Dto.LoginDto;
import com.studentAdmin.domain.User;
import com.studentAdmin.domain.UserAvatar;
import com.studentAdmin.domain.common.Common;
import com.studentAdmin.domain.common.CommonException;
import com.studentAdmin.service.*;
import com.studentAdmin.util.SessionUtil;
import com.studentAdmin.util.UrlGenerationUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:liu.yucheng
 * @Data:2019-2-17 11:23
 * @version:1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    UserFollowService userFollowService;
    @Autowired
    ArticleCollectService articleCollectService;
    @Autowired
    MessageService messageService;
    @Autowired
    ThumbsService thumbsService;
    @Deprecated
    @RequestMapping("/login.do")
    public Object userLogin(LoginDto loginDto) {
        return null;
    }

    @RequestMapping("/readArticle.do")
    @ResponseBody
    public Result readArticle(HttpServletRequest request) {
        Long articleId = Long.parseLong(request.getParameter("articleId"));
        try {
            Result result = userService.readArticle(articleId);
            return result;
        } catch (CommonException e) {
            e.printStackTrace();
            return Result.error();
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 用户发表评价
     *
     * @param articleScore
     * @param request
     * @return
     */
    @RequestMapping("/publishComment.do")
    @ResponseBody
    public Result publishComment(@RequestBody ArticleScore articleScore, HttpServletRequest request) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        articleScore.setUserId(user.getUserId());
        try {
            articleService.publishComment(articleScore);
        } catch (CommonException e) {
            return Result.error(e.getCode(), e.getMsg());
        }
        return Result.ok();
    }

    @RequestMapping("/checkComment.do")
    @ResponseBody
    public Result checkComment(HttpServletRequest request) {
        Long articleId = Long.parseLong(request.getParameter("articleId"));
        User user = (User) SessionUtil.getSessionAttribute("user");
        if (articleService.checkComment(articleId, user.getUserId()) != null) {
            return Result.error(Common.getCode_11(), Common.getMsg_11());
        }
        return Result.ok();

    }

    //头像上传控制器
    @RequestMapping("/upload.do")
    @ResponseBody
    public Result uploadAvatar(@RequestParam("picture") MultipartFile pictureFile, HttpServletRequest request) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        String originalFileName = pictureFile.getOriginalFilename();
        if (originalFileName.lastIndexOf(".") == -1) {
            return Result.error(Common.getCode_12(), Common.getMsg_12());
        }
        //获取文件后缀名
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

        String avatarUrl = UrlGenerationUtil.generateAvatarUrl(user.getUserId(), type);
        try {
            userService.uploadAvatar(pictureFile, avatarUrl);
        } catch (CommonException e) {
            return Result.error(e.getCode(), e.getMsg());
        } catch (Exception e) {
        }

        return Result.ok();
    }

    /**
     * 异步请求头像
     *
     * @param request
     * @param response
     * @param userId
     */
    @RequestMapping("/getAvatar.do_")
    public void getAvatar(HttpServletRequest request, HttpServletResponse response, @RequestParam("userId") String userId) {
        //先写死
        Long id = Long.parseLong(userId);
        List<UserAvatar> userAvatarList = userService.getUserAvatar(id);
        if (userAvatarList.size() == 0) {
            try {
                OutputStream output = response.getOutputStream();
                InputStream in;
                //设置默认头像
                File file = new File("d:\\graduation project\\user_avatar\\default.jpg");
                in = new FileInputStream(file);
                byte[] b = new byte[2048];
                while (in.read(b) != -1) {
                    output.write(b);
                }
                in.close();
                output.flush();
                output.close();
                return;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String avatarUrl = userAvatarList.get(0).getAvatarUrl();
        File file = new File(avatarUrl);
        if (file.exists()) {
            InputStream in;
            try {
                in = new FileInputStream(file);
                OutputStream output = response.getOutputStream();
                //读取2M的文件
                byte[] b = new byte[2048];
                while (in.read(b) != -1) {
                    output.write(b);
                }
                in.close();
                output.flush();
                output.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }

    /**
     * 加入个人收藏
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/addToCollect.do")
    @ResponseBody
    public Result addToCollect(@RequestParam("articleId") Long articleId) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        if (articleId == null) {
            return Result.error();
        }
        try {
            Result result = userService.collectArticle(articleId, user.getUserId());
            return result;
        } catch (CommonException e) {
            return Result.error();
        }
    }

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param limit
     * @param userName
     * @param isOrder
     * @return
     */
    @RequestMapping("/getOtherUser.do")
    @ResponseBody
    public Result getOtherUser(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, @RequestParam("userName") String userName,
                               @RequestParam("isOrder") Boolean isOrder) {
        Map<String, Object> param = new HashMap<>();
        param.put("page", page);
        param.put("limit", limit);
        param.put("userName", userName);
        param.put("isOrder", isOrder);
        QueryParam queryParam = new QueryParam(param);
        ResultPage resultPage = userService.getOtherUser(queryParam);
        return Result.ok().put("page", resultPage);

    }

    /**
     * 查询用户作品
     *
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getWorksByUserId.do")
    @ResponseBody
    public Result getWorksByUserId(@RequestParam("userId") Long userId, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        Map<String, Object> param = new HashMap<>();
        param.put("page", page);
        param.put("limit", limit);
        param.put("userId", userId);
        QueryParam queryParam = new QueryParam(param);
        ResultPage resultPage = userService.getWorksByUserId(queryParam);
        return Result.ok().put("page", resultPage);

    }

    @RequestMapping("/followOthers.do")
    @ResponseBody
    public Result followOthers(@RequestParam("userId") Long userId) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        if (user.getUserId() == userId.longValue()) {
            return Result.error(Common.getCode_15(), Common.getMsg_15());
        }
        Long followedId = userId;
        return userService.followUser(user.getUserId(), followedId);
    }

    /**
     * 获取关注用户列表
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("getFollowUserList.do")
    @ResponseBody
    public Result getFollowUserList(@RequestParam("page") long page, @RequestParam("limit") long limit) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        Map<String, Object> param = new HashMap<>();
        param.put("page", page);
        param.put("limit", limit);
        param.put("userId", user.getUserId());
        QueryParam queryParam = new QueryParam(param);
        ResultPage resultPage = userFollowService.getUserFollowList(queryParam);
        return Result.ok().put("page", resultPage);
    }

    /**
     * 取关
     *
     * @param id
     * @return
     */
    @RequestMapping("takeOffFollow.do")
    @ResponseBody
    public Result takeOffUserFollow(@RequestParam("id") long id) {
        try {
            userFollowService.takeOffById(id);
            return Result.ok();

        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * 添加个人收藏
     *
     * @param articleId
     * @return
     */
    @RequestMapping("addPersonCollect.do")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result addPersonCollect(@RequestParam("articleId") long articleId) {
        User user = (User) SessionUtil.getSessionAttribute("user");

        ArticleCollect articleCollect = articleCollectService.checkCollected(user.getUserId(), articleId);
        if (articleCollect != null) {
            return Result.error(Common.getCode_13(), Common.getMsg_13());
        }
        try {
            articleCollectService.addArticleCollect(user.getUserId(), articleId);
            return Result.ok();

        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * 获取个人收藏列表
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("getPersonCollect.do")
    @ResponseBody
    public Result getPersonCollect(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("limit", limit);
        User user = (User) SessionUtil.getSessionAttribute("user");
        map.put("userId", user.getUserId());
        QueryParam param = new QueryParam(map);
        ResultPage resultPage = articleCollectService.getUserCollect(param);
        return Result.ok().put("page", resultPage);
    }

    /**
     * 删除个人收藏
     *
     * @param id
     * @return
     */
    @RequestMapping("deleteCollectById.do")
    @ResponseBody
    public Result deleteCollectById(@RequestParam("id") long id) {
        try {
            articleCollectService.deleteById(id);
            return Result.ok();
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * 获取自己发表的文章
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("getSelfPublishArticle.do")
    @ResponseBody
    public Result getUserPublishArticle(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("limit", limit);
        map.put("userId", user.getUserId());
        QueryParam param = new QueryParam(map);
        ResultPage resultPage = articleService.getSelfPublishArticle(param);
        return Result.ok().put("page", resultPage);
    }

    /**
     * 删除文章
     *
     * @param articleId
     * @return
     */
    @RequestMapping("deleteArticleById.do")
    @ResponseBody
    public Result deleteArticleById(@Param("articleId") long articleId) {
        try {
            articleService.deleteArticle(articleId);
            return Result.ok();
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * 获取消息
     *
     * @return
     */
    @RequestMapping("getMessageById.do")
    @ResponseBody
    public Result getMessageById(@RequestParam("showHasRead") int showHasRead, @RequestParam("orderByTime") int orderByTime, @RequestParam("page") int page,
                                 @RequestParam("limit") int limit) {
        Map<String, Object> param = new HashMap<>();
        //showHasRead : 1.已读 2.未读
        param.put("showHasRead", showHasRead);
        param.put("orderByTime", orderByTime);
        param.put("page", page);
        param.put("limit", limit);
        QueryParam queryParam = new QueryParam(param);
        try {
            ResultPage resultPage = messageService.getMessage(queryParam);
            return Result.ok().put("page", resultPage);
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * 发送私信
     *
     * @param receiveId
     * @param sendMsgContentMsg
     * @return
     */
    @RequestMapping("sendMessage.do")
    @ResponseBody
    public Result sendMessage(@RequestParam("receiveId") long receiveId, @RequestParam("sendMsgContent") String sendMsgContentMsg) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        try {
            messageService.sendMessage(user.getUserId(), receiveId, sendMsgContentMsg);
            return Result.ok();
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * @param id
     * @param replyMsgContent
     * @return
     */
    @RequestMapping("replyMessage.do")
    @ResponseBody
    public Result replyMessage(@RequestParam("id") long id, @RequestParam("replyMsgContent") String replyMsgContent) {
        try {
            messageService.replyMessage(id, replyMsgContent);
            return Result.ok();
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * @param page
     * @param limit
     * @param onlyShowIgnore
     * @param showNoRead
     * @param showNoReply
     * @return
     */
    @RequestMapping("getReceiveMessage.do")
    @ResponseBody
    public Result getReceiveMessage(@RequestParam("page") int page, @RequestParam("limit") int limit,
                                    @RequestParam("onlyShowIgnore") boolean onlyShowIgnore,
                                    @RequestParam("showNoRead") boolean showNoRead,
                                    @RequestParam("showNoReply") boolean showNoReply ){
        User user = (User) SessionUtil.getSessionAttribute("user");
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("limit", limit);
        map.put("receiveId", user.getUserId());
        map.put("onlyShowIgnore", onlyShowIgnore);
        map.put("showNoRead", showNoRead);
        map.put("showNoReply",showNoReply);
        QueryParam param = new QueryParam(map);
        ResultPage resultPage = messageService.getMessageByReceiveId(param);
        return Result.ok().put("page", resultPage);
    }

    /**
     * 查询已发送私信
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("getSendMessage.do")
    @ResponseBody
    public Result getSendMessage(@RequestParam("page") int page, @RequestParam("limit") int limit,@RequestParam("onlyShowHasReply") boolean onlyShowHasReply) {
        User user = (User) SessionUtil.getSessionAttribute("user");
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("limit", limit);
        map.put("sendId",user.getUserId());
        map.put("onlyShowHasReply",onlyShowHasReply);
        QueryParam param = new QueryParam(map);
        ResultPage resultPage = messageService.getMessageBySendId(param);
        return Result.ok().put("page", resultPage);
    }

    /**
     * 设置私信已读状态
     * @param id
     * @return
     */
    @RequestMapping("setMessageRead.do")
    @ResponseBody
    public Result setMessageRead(@RequestParam("id") long id){
        try{
            messageService.readMessageById(id);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }
    }

    /**
     * 发送者删除
     * @param id
     * @return
     */
     @RequestMapping("senderDelete.do")
     @ResponseBody
    public Result senderDelete(@RequestParam("id") long id ){
          try{
              messageService.senderDeleteById(id);
              return  Result.ok();
          }catch (Exception e){
              return  Result.error();
          }
     }
    /**
     * 接收着删除
     * @param id
     * @return
     */
     @RequestMapping("receiverDelete.do")
    @ResponseBody
    public Result receiverDelete(@RequestParam("id") long id)
     {
         try{
             messageService.receiveDeleteById(id);
             return  Result.ok();
         }catch (Exception e){
             return  Result.error();
         }
     }
     @RequestMapping("ignoreMessage.do")
     @ResponseBody
    public Result ignoreMessage(@RequestParam("id") long id){
         try{
             messageService.ignoreMessageById(id);
             return  Result.ok();
         }catch (Exception e){
             return  Result.error();
         }
     }

    /**
     * 点赞
     * @param userId
     * @return
     */
     @RequestMapping("thumb.do")
     @ResponseBody
    public Result thumb(@RequestParam("userId") long userId){
         User user = (User) SessionUtil.getSessionAttribute("user");
         return  thumbsService.insertThumbs(userId,user.getUserId());
     }


}
