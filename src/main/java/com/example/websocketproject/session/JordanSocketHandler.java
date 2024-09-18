package com.example.websocketproject.session;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class JordanSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);
        session.sendMessage(new TextMessage("Echo: " + payload));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        // Send a message to the client
        String responseMessage = "WebSocket handshake completed successfully.\n" +
                "WebSocket Handler: " + this.getClass().getSimpleName();
        session.sendMessage(new TextMessage(responseMessage));

        // Log to console
        System.out.println(responseMessage);
    }
}
