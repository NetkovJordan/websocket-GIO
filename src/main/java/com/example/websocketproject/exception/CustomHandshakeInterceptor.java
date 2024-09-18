package com.example.websocketproject.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

public class CustomHandshakeInterceptor implements HandshakeInterceptor {

    private static final List<String> VALID_SUB_PROTOCOLS = List.of("chat", "graphql-ws", "json-stream");

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {

        if (request instanceof ServletServerHttpRequest && response instanceof ServletServerHttpResponse) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();

            String subprotocol = servletRequest.getHeader("Sec-WebSocket-Protocol");
            if (subprotocol == null || !VALID_SUB_PROTOCOLS.contains(subprotocol)) {
                System.err.println("Invalid Sec-WebSocket-Protocol header: " + subprotocol);
                servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return false;
            }

            servletResponse.addHeader("Sec-WebSocket-Protocol", subprotocol);
        }

        return true;
    }

    @Override
    public void afterHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Exception exception) {

        if (exception != null) {
            System.err.println("WebSocket handshake failed with exception: " + exception.getMessage());
        } else {
            System.out.println("WebSocket handshake completed successfully.");
            System.out.println("WebSocket Handler: " + wsHandler.getClass().getSimpleName());
        }
    }
}
