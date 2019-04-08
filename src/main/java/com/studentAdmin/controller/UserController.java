package com.studentAdmin.controller;

import com.common.Result;
import com.studentAdmin.domain.Dto.LoginDto;
import com.studentAdmin.domain.common.CommonException;
import com.studentAdmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author:liu.yucheng
 * @Data:2019-2-17 11:23
 * @version:1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 相应登录请求
     *
     * @param loginDto
     * @return
     */
    @Autowired
    UserService userService;

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
}
