package br.com.gustavobarbozamarques.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${keycloak.client.name}")
    private String clientName;

    @Bean
    public JwtAuthenticationConverter JwtAuthenticationConverter() {
        var jwtGrantedAuthoritiesConverter = jwtGrantedAuthoritiesConverter();
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    private Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
        return jwt -> {
            List<String> roles = new ArrayList<>();
            try {
                Map resourceAccess = jwt.getClaimAsMap("resource_access");
                Map client = (Map) resourceAccess.get(clientName);
                roles.addAll((List<String>) client.get("roles"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        };
    }
}
