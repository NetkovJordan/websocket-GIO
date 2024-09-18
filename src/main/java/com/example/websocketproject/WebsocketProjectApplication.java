package com.example.websocketproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class WebsocketProjectApplication {

    private static final Logger logger = LoggerFactory.getLogger(WebsocketProjectApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebsocketProjectApplication.class, args);
        logger.info("WebSocket application is running!");
        logger.info("Establish a connection on ws://localhost:8080/ws!");
        logger.info("Make sure to connect with header 'Sec-WebSocket-Protocol'");
        logger.info("Accepted values for header 'Sec-WebSocket-Protocol': [chat, graphql-ws, json-stream]");
    }

}
