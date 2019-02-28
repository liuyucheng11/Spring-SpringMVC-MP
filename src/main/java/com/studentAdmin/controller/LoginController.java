package com.studentAdmin.controller;

import com.studentAdmin.domain.Dto.LoginDto;
import com.studentAdmin.domain.User;
import com.studentAdmin.domain.VOs.UserVO;
import com.studentAdmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:"liu.yucheng",
 * @Data:$Date
 */
@RequestMapping("/login")
@Controller("loginController")
public class LoginController {
    //相应login请求
    @Autowired
    UserService userService;

    @RequestMapping("/login.do_")
    @ResponseBody
    public Map doLogin( @RequestBody LoginDto loginDto) {
        Map<String, Object> loginResult = new HashMap<>();
        Map qryResult = userService.userLogin(loginDto.getUserName(), loginDto.getPassword());
        //防止报错
        if (qryResult.get("isExist") != null) {
            //校验成功
            if ((boolean) qryResult.get("isExist") && qryResult.get("user") != null) {
                User user = (User) qryResult.get("user");
                //防止密码泄露
                user.setUserPassword("**********");
                loginResult.put("exist", true);
                loginResult.put("user", user);
            } else {
                loginResult.put("passwordWrong", true);
            }
        } else {
            loginResult.put("serverError", true);
        }
        return loginResult;
    }
}
