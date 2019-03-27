package com.studentAdmin.controller;

import com.common.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.studentAdmin.dao.mapper.ArticleMapper;
import com.studentAdmin.domain.Article;
import com.studentAdmin.domain.VOs.ArticleScoreVO;
import com.studentAdmin.domain.VOs.ArticleVO;
import com.studentAdmin.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    ArticleServiceImpl articleService;

    /**
     * 分页查询
     *
     * @param request
     * @param
     * @param
     * @return
     */
    @RequestMapping("/articleSearch.do")
    @ResponseBody
    public PageInfo<Article> ArticleSearch(HttpServletRequest request) {
        String URI = request.getRequestURI();
        HttpSession session = request.getSession();
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));

        boolean isNewSession = session.isNew();
        /*默认页号为 1 ,大小为 10*/
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleMapper.searchArticle("");
        return new PageInfo<Article>(list);
    }

    /**
     * 评论分页
     *
     * @param articleId
     * @param request
     * @return
     */
    @RequestMapping("/qryArticleComment.do")
    @ResponseBody
    public PageInfo<ArticleScoreVO> qryArticleScoreVOs(long articleId, HttpServletRequest request) {
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        List<ArticleScoreVO> list = articleService.qryArticleComments(articleId);
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<ArticleScoreVO>(list);
    }

    @RequestMapping("/qryArticleInfo.do")
    @ResponseBody
    public ArticleVO qryArticleInfo(long articleId) {
        ArticleVO articleVO = articleService.qryArticleInfo(articleId);
        return articleVO;
    }

    /**
     * 发表文章
     * @param request
     * @return
     */
    @RequestMapping("/publishArticle.do")
    @ResponseBody
    public Result publishArticle(HttpServletRequest request){
         String content = request.getParameter("content");
         String articleName = request.getParameter("articleName");
         String articleDesc = request.getParameter("articleDesc");
         String articleType = request.getParameter("articleType");
         Article article = new Article(articleName,Long.parseLong(articleType),articleDesc);
        return articleService.publishArticle(article,content);
    }
}





