package com.interceptor;

import com.common.Result;
import com.studentAdmin.domain.common.Common;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author:liu.yucheng
 * @Data:2019-4-8 11:12
 * @version:1.0
 */
public class InterceptorImpl implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断是否登录进行拦截请求
        PrintWriter printWriter = response.getWriter();
        if (request.getSession().getAttribute("user") == null && request.getRequestURI().endsWith("*.do") == true) {
            //设置返回值，前端可获取错误信息
            Result result =   Result.error(Common.getCode_10(),Common.getMsg_10());
            JSONObject jsonObject = JSONObject.fromObject(result);
            printWriter.write(jsonObject.toString());
            printWriter.flush();
            printWriter.close();
            return  false;
        }
        return true;
    }

    @Override
    /**
     * 可用于打印日志操作
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    /**
     * 记录
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
