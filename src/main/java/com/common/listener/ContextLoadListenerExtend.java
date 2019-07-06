package com.common.listener;

import com.common.GlobalMessage;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * @author: liu.yucheng
 * @Date: 2019-7-6  10:04
 * @version: 1.0
 */
public class ContextLoadListenerExtend extends ContextLoaderListener {

    /**
     * 初始化全局配置 继承 Spring上下文环境配置
     *
     * @param event
     * @see GlobalMessage
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {

        String realPath = event.getServletContext().getRealPath("/");
        GlobalMessage.setPath(realPath);
        String projectName = event.getServletContext().getInitParameter("projectName");
        String projectPath;
        if (StringUtils.isBlank(projectName)) {
            projectPath = event.getServletContext().getContextPath() + "/";
        } else {
            projectPath = "/" + projectName + "/";
        }
        GlobalMessage.setProjectPath(projectPath);
        super.contextInitialized(event);

    }
}
