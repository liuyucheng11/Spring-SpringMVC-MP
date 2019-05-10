package com.studentAdmin.webSocket;

import com.studentAdmin.domain.User;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * webSocket 拦截
 * @author: liu.yucheng
 * @Date: 2019-5-8  15:30
 * @version: 1.0
 */
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, org.springframework.web.socket.WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
               // String userName = (String) session.getAttribute("user");
                String userName;
                //这边获得登录时设置的唯一用户标识
                User user =(User)session.getAttribute("user");
                long userId = user.getUserId();
                if (user == null) {
                    userName = "未知" + session.getId();
                }
                map.put("WEBSOCKET_USERNAME", user.getUserName());
                map.put("WEBSOCKET_USERID",userId);
                //将用户标识放入参数列表后，下一步的websocket处理器可以读取这里面的数据
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, org.springframework.web.socket.WebSocketHandler webSocketHandler, Exception e) {
        System.out.println("After Handshake");

    }
}

