package tn.esprit.khotwa_ms.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.util.CollectionUtils;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtAuthenticationConverter jwtAuthenticationConverter;

    public KeycloakJwtAuthenticationConverter() {
        this.jwtAuthenticationConverter = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix("ROLE_"); // Ensures role names match Spring Security's format
        authoritiesConverter.setAuthoritiesClaimName("realm_access");
        this.jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<String> roles = extractRoles(jwt);
        return jwtAuthenticationConverter.convert(jwt);
    }

    private Collection<String> extractRoles(Jwt jwt) {
        if (jwt.getClaim("realm_access") != null) {
            List<String> roles = jwt.getClaim("realm_access.roles");
            if (!CollectionUtils.isEmpty(roles)) {
                return roles.stream()
                        .map(role -> "ROLE_" + role.toUpperCase()) // Ensures compatibility with Spring Security
                        .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }
}
