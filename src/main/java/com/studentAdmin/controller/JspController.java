package com.studentAdmin.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping("/SecondPage.do_")
    public String getSecondPage(HttpServletRequest request, Mod mod){
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i <6 ; i++) {
            stringList.add("aaa"+i);
        }
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i <6 ; i++) {
            integerList.add(i);
        }
        request.setAttribute("stringList",stringList);
        request.setAttribute("intList",integerList);
        return secondPage;
    }
    @RequestMapping("/thirdPage.do_")
    public String getThirdPage(){
        return third;
    }
}
