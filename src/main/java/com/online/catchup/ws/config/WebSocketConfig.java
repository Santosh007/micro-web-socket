package com.online.catchup.ws.config;

import com.online.catchup.ws.handler.SingleSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(socketHandler(), "/{user}")
                .setAllowedOrigins("*")
                .addInterceptors(new SocketHandshakeInterceptor());
    }

    @Bean
    public SingleSocketHandler socketHandler() {
        return new SingleSocketHandler();
    }

    private class SocketHandshakeInterceptor implements HandshakeInterceptor {
        @Override
        public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
            HttpServletRequest origRequest =
                    ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
            Map<String, String> uriTemplateVars =
                    (Map<String, String>) origRequest
                            .getAttribute(
                                    HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            if (uriTemplateVars != null) {
                attributes.putAll(uriTemplateVars);
            }

            return true;
        }

        @Override
        public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

        }
    }
}
