package com.studentAdmin.controller;

import com.studentAdmin.domain.Dto.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:liu.yucheng
 * @Data:2019-2-17 11:23
 * @version:1.0
 */
@Controller
public class UserController {
    /**
     * 相应登录请求
     * @param loginDto
     * @return
     */
    @RequestMapping("/login.do")
    public Object userLogin(LoginDto loginDto){
        return null;
    }
}
