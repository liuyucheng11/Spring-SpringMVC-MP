package com.studentAdmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:"liu.yucheng",
 * @Data:$Date
 */
@Controller("loginController")
public class LoginController extends AbstractController {
    //相应login请求
    @Override
    public ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse){
          ModelAndView mv = new ModelAndView();
          return  mv;
    }

}
