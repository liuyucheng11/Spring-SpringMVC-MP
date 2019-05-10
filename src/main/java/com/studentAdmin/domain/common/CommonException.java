package com.studentAdmin.domain.common;

/**
 * 自定义异常处理类
 *
 * @author:liu.yucheng
 * @Data:2019-3-27 13:58
 * @version:1.0
 */
public class CommonException extends Exception {
    private String msg;
    private int code = 500;

    public CommonException(String msg) {
        super(msg);
        this.msg = msg;
    }
    public CommonException(int code ,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
