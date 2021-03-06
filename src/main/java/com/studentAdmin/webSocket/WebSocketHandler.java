package com.studentAdmin.webSocket;

import com.alibaba.fastjson.JSON;
import com.studentAdmin.domain.Dto.MessageDto;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * webSocket处理器
 *
 * @author: liu.yucheng
 * @Date: 2019-5-8  15:26
 * @version: 1.0
 */
public class WebSocketHandler extends TextWebSocketHandler {

    // 已建立连接的用户
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();

    /**
     * 处理前端发送的文本信息 js调用websocket.send时候，会调用该方法
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String senderName = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        long senderId = (long) session.getAttributes().get("WEBSOCKET_USERID");
        // 获取提交过来的消息详情
        System.out.println("收到用户 " + senderName + " 的消息:" + message.toString());
        //传入消息由json转为 Message对象
        MessageDto messageDto = JSON.parseObject(message.getPayload(), MessageDto.class);
        messageDto.setSenderId(senderId);
        messageDto.setSenderName(senderName);
        // 遍历所有已连接用户
        long target = messageDto.getReceiverId();
        for (WebSocketSession user : users) {
            if ((long) user.getAttributes().get("WEBSOCKET_USERID") == (target)) {
                //遇到匹配用户 连接正常则发送消息
                if (user.isOpen()) {
                    messageDto.setMessageType(1);
                    sendMessageToUser(target, new TextMessage(messageDto.toString()));
                } else {//若异常则发送失败
                    messageDto.setMessageType(2);
                    messageDto.setMessageContent("对方在线异常,发送失败");
                    sendMessageToUser(senderId, new TextMessage(messageDto.toString()));
                }
                return;
            }
        }
        //未找到匹配用户 发送失败
        messageDto.setMessageContent("404对方暂时不在线");
        messageDto.setMessageType(2);
        sendMessageToUser(senderId, new TextMessage(messageDto.toString()));
    }

    /**
     * 当新连接建立的时候，被调用 连接成功时候，会触发页面上onOpen方法
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        users.add(session);
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        System.out.println("用户 " + username + " Connection Established");
        session.sendMessage(new TextMessage(username + " connect"));

    }

    /**
     * 当连接关闭时被调用
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        System.out.println("用户 " + username + " Connection closed. Status: " + status);
        users.remove(session);
    }

    /**
     * 传输错误时调用
     *
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        if (session.isOpen()) {
            session.close();
        }
        System.out.println("用户: " + username + " websocket connection closed......");
        users.remove(session);
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给某个用户发送消息
     *
     * @param userId
     * @param message
     */
    public void sendMessageToUser(long userId, TextMessage message) {
        for (WebSocketSession user : users) {
            if ((long) user.getAttributes().get("WEBSOCKET_USERID") == userId) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}