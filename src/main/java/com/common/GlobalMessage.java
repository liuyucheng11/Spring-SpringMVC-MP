package com.common;

import java.io.File;

/**
 * 初始化项目全局信息
 * @author: liu.yucheng
 * @Date: 2019-7-6  9:29
 * @version: 1.0
 */
public final class GlobalMessage {

    //项目路径
    private static String projectPath;

    private static String projectName;

    private static String contextPath;

    private static String defaultLogfilePath = System.getProperty("catalina.home") + File.separator + "logs" + File.separator;

    public static String getProjectPath() {
        return projectPath;
    }

    public static String getDefaultLogfilePath() {
        return defaultLogfilePath;
    }

    public static void setPath(String path) {
        contextPath = path;
    }

    public static void setDefaultLogfilePath(String defaultLogfilePath) {
        GlobalMessage.defaultLogfilePath = defaultLogfilePath;
    }

    public static void setProjectPath(String projectPath) {
        GlobalMessage.projectPath = projectPath;
        projectName = projectPath.substring(1, projectPath.length() - 1);


    }

    public static String getProjectName() {
        return projectName;
    }

    public static void setProjectName(String projectName) {
        GlobalMessage.projectName = projectName;
    }
}
