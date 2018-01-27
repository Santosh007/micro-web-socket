package com.online.catchup.ws.service;

import com.online.catchup.aspect.Loggable;
import com.online.catchup.ws.repo.WebSocketSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collection;

@Service
public class WebSocketSessionServiceImpl implements WebSocketSessionService {

    @Autowired
    private WebSocketSessionRepository repository;

    @Loggable
    @Override
    public void registerSession(WebSocketSession session) {
        repository.storeSession(session);
    }

    @Loggable
    @Override
    public void unregisterSession(WebSocketSession session) {
        repository.removeSession(session);
    }

    @Override
    public Collection<WebSocketSession> fetchSessions() {
        return repository.getSessions();
    }

    @Override
    public WebSocketSession fetchSession(WebSocketSession session) {
        return repository.getSession(session.getId());
    }
}
