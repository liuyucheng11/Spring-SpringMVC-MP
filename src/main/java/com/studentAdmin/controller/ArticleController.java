package com.studentAdmin.controller;

import com.studentAdmin.dao.mapper.ArticleMapper;
import com.studentAdmin.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author:liu.yucheng
 * @Data:2019-2-17 11:24
 * @version:1.0
 */
@RequestMapping("/article")
@Controller
public class ArticleController {
    @Autowired
    ArticleMapper articleMapper;
     @RequestMapping("/articleSearch.do")
     @ResponseBody
    public List<Article> ArticleSearch(HttpServletRequest request){
         String URI = request.getRequestURI();
         HttpSession session = request.getSession();
         boolean isNewSession = session.isNew();

         return  articleMapper.searchArticle("");
     }

}
