package com.online.catchup.ws.handler;

import com.online.catchup.ws.service.WebSocketSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.io.UncheckedIOException;

@Component
public class SingleSocketHandler extends TextWebSocketHandler {
    @Autowired
    private WebSocketSessionService service;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        service.fetchSessions().forEach((v) -> {
            if (!v.getId().equals(session.getId())) {
                try {
                    if (v.isOpen())
                        v.sendMessage(message);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }

            }
        });
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        service.registerSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        service.unregisterSession(session);
    }


}
