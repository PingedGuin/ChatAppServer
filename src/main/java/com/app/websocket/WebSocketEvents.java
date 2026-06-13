package com.app.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebSocketEvents {
    private final Set<Long> onlineUsers =
            ConcurrentHashMap.newKeySet();

    @EventListener
    public void handleConnect(SessionConnectedEvent event) {

        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(event.getMessage());


        if (accessor.getUser() == null) {
            return;
        }
        String userId = accessor.getUser().getName();

        if (userId.equals("anonymous")) {
            return;
        } // todo remove this


        if (accessor.getUser() == null) {
            return;
        }


        onlineUsers.add(Long.parseLong(userId));
        log.info("CONNECTED: {}", userId);
    }

    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event) {

        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(event.getMessage());

        if (accessor.getUser() == null) {
            return;
        }
        if (accessor.getUser() == null) {
            return;
        }
        var userId = accessor.getUser().getName();

        System.out.println(userId);

        if (userId.equals("anonymous")) {
            return;
        } // todo remove this

        onlineUsers.remove(userId);
        log.info("DISCONNECTED: {}", userId);
    }
    @EventListener
    public void handleSubscribe(SessionSubscribeEvent event) {

        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(event.getMessage());

        String destination = accessor.getDestination();

        if (destination == null) {
            return;
        }

        String userId = accessor.getUser().getName();

        log.info("User {} subscribed to {}", userId, destination);
    }
}
