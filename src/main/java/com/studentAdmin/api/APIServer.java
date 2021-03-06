package com.studentAdmin.api;

import com.common.GlobalMessage;
import com.container.APIHandler;
import com.container.AdapterContainer;
import com.container.ContainerManager;
import com.container.RequestContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 模拟实现 api
 *
 * @author: liu.yucheng
 * @Date: 2019-7-6  8:43
 * @version: 1.0
 */
@Controller("APIService")
public class APIServer {
    APIServer() {
        //伴随Spring容器启动加载
        ContainerManager.initAdapterContainer();
        ContainerManager.initServiceComContainer();
        ContainerManager.init();
    }

    @RequestMapping("/isAPIService")
    @ResponseBody
    public Object dealHttp(HttpServletRequest request, HttpServletResponse response) {
        String apiId = request.getRequestURI();

        String a = apiId.replace( GlobalMessage.getProjectPath(),"/");
        return doApiServiceCall(a);
    }

    /**
     * 核心处理
     * @param apiId
     * @return
     */
    private Object doApiServiceCall(String apiId) {
        RequestContext.initAPIConfig(apiId);
        APIHandler apiHandler = ContainerManager.getAdapterContainer();
        apiHandler.handle();
        Object result = RequestContext.getDTO();
        return result;
    }
}
