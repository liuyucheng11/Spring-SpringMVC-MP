package com.studentAdmin.domain.VOs;

import com.studentAdmin.domain.ChatMessage;

/**
 * @author: liu.yucheng
 * @Date: 2019-5-5  16:44
 * @version: 1.0
 */
public class ChatMessageVO extends ChatMessage {
    private String senderName;
    private String receiverName;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}
