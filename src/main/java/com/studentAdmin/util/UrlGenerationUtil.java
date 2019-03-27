package com.studentAdmin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文章目录生成器
 *
 * @author:liu.yucheng
 * @Data:2019-3-18 16:14
 * @version:1.0
 */
public class UrlGenerationUtil {
    //生成唯一的文件目录
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String generateUrl(String catalog, Long userId) {
        StringBuffer stringBuffer = new StringBuffer();
        Date date = new Date();
        String dateFormat = sdf.format(date);
        stringBuffer.append(catalog).append(userId.toString()).append(dateFormat).append(".txt");
        return  stringBuffer.toString();
    }
}
