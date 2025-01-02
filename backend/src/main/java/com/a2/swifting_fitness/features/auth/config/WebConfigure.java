package com.a2.swifting_fitness.features.auth.config;//package com.a2.pickyami.game.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//public class WebConfigure implements WebSocketConfigurer {
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(gameHandler(), "/game").setAllowedOrigins("*").setHandshakeHandler(handshakeHandler());
//
//    }
//
//    @Bean
//    public WebSocketHandler gameHandler() {
//        return new CustonWebSocketHandler();
//    }
//
//    @Bean
//    public CustomHandshakeHandler handshakeHandler() {
//        return new CustomHandshakeHandler();
//    }
//}
