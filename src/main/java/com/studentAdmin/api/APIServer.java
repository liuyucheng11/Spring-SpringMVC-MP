package com.studentAdmin.api;

import com.container.ContainerManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  模拟实现 api
 * @author: liu.yucheng
 * @Date: 2019-7-6  8:43
 * @version: 1.0
 */
@Controller("APIService")
public class APIServer {
    APIServer(){

        //伴随Spring容器启动加载
        ContainerManager.initAdapterContainer();
        ContainerManager.initServiceComContainer();
    }
    @RequestMapping("/isAPIService")
    @ResponseBody
    public Object dealHttp(HttpServletRequest request, HttpServletResponse response){
        int a;
        return null;
    }
}
