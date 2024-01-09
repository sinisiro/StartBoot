package com.sinisiro.StartBoot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /*
    registerStompEndPoints
    소켓 연결과 관련된 설정.
    addEndpoint() : 소켓 연결 uri.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    /*
    해당 메소드는 Stomp 사용을 위한 Message Broker 설정을 해주는 메소드이다.
    enableSimpleBroker(”/queue”, “/topic”) :
    메세지를 받을 때, 경로를 설정해주는 함수이다.
    스프링에서 제공해주는 내장 브로커를 사용하는 함수이다. 또한 “/queue”, “/topic”을 통해 1:1, 1:N 설정을 해준다.
    “/queue”, “/topic”가 api에 prefix로 붙은 경우, messageBroker가 해당 경로를 가로챈다.

    setApplicationDestinationPrefixes(”/app”) :
    메세지를 보낼 때, 관련 경로를 설정해주는 함수이다.
    클라이언트가 메세지를 보낼 떄, 경로 앞에 “/app”이 붙어있으면 Broker로 보내진다.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}