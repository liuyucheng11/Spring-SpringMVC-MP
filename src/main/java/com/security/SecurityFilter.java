package com.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 实现安全类
 * 主要用于过滤未登录产生的不安全请求
 *
 * @author:liu.yucheng
 * @Data:2019-2-18 17:54
 * @version:1.0
 */

public class SecurityFilter implements Filter {
    FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest hostRequest = (HttpServletRequest) servletRequest;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        //当前请求为登录不过滤
        String loginRequest = hostRequest.getContextPath() + config.getInitParameter("doLogin");
        //其他请求
        String resourcesRequest = hostRequest.getContextPath() + config.getInitParameter("getData");
        //获取请求结尾 like:article/show.do user/show.do
        String requestURI = hostRequest.getRequestURI();
        HttpSession httpSession = hostRequest.getSession();
        System.out.println(hostRequest.getSession().getId());
        if (requestURI.endsWith(".do") && httpSession.getAttribute("user") == null) {
            try {
                wrapper.sendRedirect("/error");
                return;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            //链式处理
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
    @Override
    public void destroy() {
        this.config = null;
    }
}
