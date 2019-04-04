package com.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果类
 *
 * @author:liu.yucheng
 * @Data:2019-3-16 16:06
 * @version:1.0
 */
public class Result extends HashMap<String, Object> {

    public Result() {
        put("code", 0);
    }

    public static Result error() {
        return error(500, "未知异常请联系管理员");
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }

    public static Result ok(Map<String, Object> map) {
        Result result = new Result();
        result.putAll(map);
        return result;
    }

    public static Result ok() {
        return new Result();
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }


}
