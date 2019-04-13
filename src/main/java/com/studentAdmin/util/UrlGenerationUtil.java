package com.studentAdmin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 目录生成器
 *
 * @author:liu.yucheng
 * @Data:2019-3-18 16:14
 * @version:1.0
 */
public class UrlGenerationUtil {
    //生成唯一的文件目录
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    //本地头像存取目录
    private static final String avatarCatalog = "d:\\graduation project\\user_avatar\\";

    public static String generateUrl(String catalog, Long userId) {
        StringBuffer stringBuffer = new StringBuffer();
        Date date = new Date();
        String dateFormat = sdf.format(date);
        stringBuffer.append(catalog).append(userId.toString()).append(dateFormat).append(".txt");
        return stringBuffer.toString();
    }
    //生成头像目录
    public static String generateAvatarUrl( Long userId, String fileType) {
        StringBuffer stringBuffer = new StringBuffer(avatarCatalog);
        stringBuffer.append(userId).append(".").append(fileType);
        return stringBuffer.toString();
    }
}
