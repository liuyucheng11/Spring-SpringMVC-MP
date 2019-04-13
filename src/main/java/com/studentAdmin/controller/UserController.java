package com.studentAdmin.controller;

import com.common.Result;
import com.studentAdmin.domain.ArticleScore;
import com.studentAdmin.domain.Dto.LoginDto;
import com.studentAdmin.domain.User;
import com.studentAdmin.domain.UserAvatar;
import com.studentAdmin.domain.common.Common;
import com.studentAdmin.domain.common.CommonException;
import com.studentAdmin.service.ArticleService;
import com.studentAdmin.service.UserService;
import com.studentAdmin.util.SessionUtil;
import com.studentAdmin.util.UrlGenerationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

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
            userService.collectArticle(articleId, user.getUserId());
            return Result.ok();
        } catch (CommonException e) {
            return Result.error();
        }
    }
}
