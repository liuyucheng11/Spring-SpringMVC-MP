package com.router;

import com.common.GlobalMessage;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 路由实现器
 * @see com.studentAdmin.api.APIServer
 * @author: liu.yucheng
 * @Date: 2019-7-6  8:44
 * @version: 1.0
 */
public class Router extends RequestMappingHandlerMapping {

    @Override
    protected HandlerMethod lookupHandlerMethod(String lookupPath, HttpServletRequest request) throws Exception {
        //查找SpringMVC默认注解配置
        HandlerMethod defaultHandlerMethod = super.lookupHandlerMethod(lookupPath, request);
        if (defaultHandlerMethod != null) {
            return defaultHandlerMethod;
        }
        //以api开头的请求交由APIServer类进行处理
        for (HandlerMethod handlerMethod : super.getHandlerMethods().values()) {
            boolean isAPI = handlerMethod.getBean().equals("APIService");
            if (request.getRequestURI().startsWith(GlobalMessage.getProjectPath()+"api/") && isAPI) {
                return handlerMethod;
            }
        }
        return null;
    }
}
