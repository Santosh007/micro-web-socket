package com.online.catchup.ws.repo;

import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class WebSocketSessionRepository {

    Map<String, WebSocketSession> sessionMap;

    public WebSocketSessionRepository() {
        this.sessionMap = new ConcurrentHashMap<>();
    }

    public void storeSession(WebSocketSession session) {
        sessionMap.put(session.getId(), session);
    }

    public Collection<WebSocketSession> getSessions() {
        return sessionMap.values();
    }

    public WebSocketSession getSession(String id) {
        return sessionMap.get(id);
    }

    public void removeSession(String id) {
        sessionMap.remove(id);
    }

    public void removeSession(WebSocketSession session) {
        sessionMap.remove(session.getId());
    }
}
