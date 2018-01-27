package com.online.catchup.ws.service;

import org.springframework.web.socket.WebSocketSession;

import java.util.Collection;

public interface WebSocketSessionService {

    public void registerSession(WebSocketSession session);

    public void unregisterSession(WebSocketSession session);

    public Collection<WebSocketSession> fetchSessions();

    public WebSocketSession fetchSession(WebSocketSession session);

}
