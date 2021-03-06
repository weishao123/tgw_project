package com.tgw360.websocket;

import com.tgw360.management.SocketSessionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * STOMP监听类
 * 用于session注册 以及key值获取
 */
public class STOMPConnectEvent implements ApplicationListener<SessionConnectEvent> {

    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;
    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        //login get from browser
        String user = sha.getUser().getName();
        String agentId = sha.getNativeHeader("login").get(0);
        String sessionId = sha.getSessionId();
        webAgentSessionRegistry.registerSessionId(user,sessionId);
    }

}
