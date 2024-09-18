package com.example.websocketproject.config;

import com.example.websocketproject.exception.CustomHandshakeInterceptor;
import com.example.websocketproject.session.JordanSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new JordanSocketHandler(), "/ws")
                .addInterceptors(new CustomHandshakeInterceptor())
                .setAllowedOrigins("*"); // Set allowed origins as needed
    }
}
