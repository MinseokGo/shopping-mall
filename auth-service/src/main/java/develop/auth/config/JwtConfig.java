package develop.auth.config;

import develop.auth.domain.AuthTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token-validity}")
    private long tokenValidity;

    @Bean
    public AuthTokenProvider authTokenProvider() {
        return new AuthTokenProvider(secretKey, tokenValidity);
    }
}
