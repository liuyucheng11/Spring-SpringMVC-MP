package com.studentAdmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author:liu.yucheng
 * @Data:2019-2-19 14:03
 * @version:1.0
 */
@Controller
public class JspController {
    final String  firstPage = "/first";
    final String secondPage ="/second";
    final String third = "/third";
    @RequestMapping("/firstPage.do")
    public ModelAndView getFirstPage(HttpServletRequest request){

        String path = request.getContextPath();
        HttpSession Session = request.getSession();
           Session.getId();
           Session.isNew();
        ModelAndView mv = new ModelAndView(firstPage);
        return mv;
    }
    @RequestMapping("/SecondPage.do")
    public String getSecondPage(){
        return secondPage;
    }
    @RequestMapping("/thirdPage.do")
    public String getThirdPage(){
        return third;
    }
}
