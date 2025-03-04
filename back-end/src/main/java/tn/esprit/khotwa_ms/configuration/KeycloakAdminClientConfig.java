package tn.esprit.khotwa_ms.configuration;

/*import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import javax.ws.rs.core.Response;*/
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class KeycloakAdminClientConfig {
/*
    @Value("${keycloak.auth-server-url}")
    private String keycloakAuthServerUrl;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${keycloak.client-id}")
    private String keycloakClientId;

    @Value("${keycloak.client-secret}")
    private String keycloakClientSecret;
    @Bean
    public Keycloak keycloakAdminClient() {
        return Keycloak.getInstance(
                keycloakAuthServerUrl,
                "master", // This is the realm used for authentication (usually "master" for admin tasks)
                keycloakClientId,
                keycloakClientSecret,
                keycloakRealm
        );
    }

    @Bean
    public UsersResource usersResource(Keycloak keycloak) {
        return keycloak.realm(keycloakRealm).users();
    }

 */
}
