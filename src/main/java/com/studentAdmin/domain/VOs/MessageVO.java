package com.studentAdmin.domain.VOs;

import com.studentAdmin.domain.Message;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-28  12:22
 * @version: 1.0
 */
public class MessageVO extends Message {
    //发送人名
    private String sendName;
    //接收人名
    private String receiveName;

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }
}
