package com.thirty.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by ByeongChan on 2018. 1. 20..
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        //메모리 기반 메세지 브로커가 해당 api를 구독하고 있는 클라이언트에게 메세지 전달
        config.enableSimpleBroker("/topic/");
        //서버에서 클라이언트로부터의 메세지를 받을 api의 prefix
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        //여러가지 End Points 설정
        registry.addEndpoint("/websockethandler").withSockJS();
    }
}
