package tn.esprit.khotwaback.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocket implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Activer un broker simple pour les topics publics et privés
        config.enableSimpleBroker("/topic", "/queue");

        // Préfixe pour les messages envoyés par l'application
        config.setApplicationDestinationPrefixes("/app");

        // Préfixe pour les messages privés (utilisé pour les messages point-à-point)
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint pour la connexion WebSocket
        registry.addEndpoint("/ws").withSockJS();
    }
}