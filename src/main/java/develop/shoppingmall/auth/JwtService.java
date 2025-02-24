package develop.shoppingmall.auth;

import static develop.shoppingmall.auth.JwtUtils.*;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private static final Logger log = LoggerFactory.getLogger(JwtService.class);

    private final JwtProperties jwtProperties;

    JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String create(String email) {
        return TOKEN_PREFIX + Jwts.builder()
                .subject(SHOPPING_MALL_AUTH_SUBJECT)
                .issuer(TOKEN_PROVIDER)
                .audience()
                .and().id(email)
                .claim(TOKEN_SIGN_CLAIM, email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + MILLISECONDS * SECONDS * MINUTES * HOURS))
                .signWith(
                        new SecretKeySpec(
                                jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8),
                                SIG.HS256.key().build().getAlgorithm()
                        )
                )
                .compact();
    }

    public String parse(String token) {
        validTokenPrefix(token);

        try {
            return extractEmail(token);
        } catch (ExpiredJwtException e) {
            log.error("[ERROR] 만료된 토큰입니다.", e);
            throw new IllegalArgumentException("[ERROR] 만료된 토큰입니다.", e);
        } catch (UnsupportedJwtException e) {
            log.error("[ERROR] 지원하지 않는 토큰입니다.", e);
            throw new IllegalArgumentException("[ERROR] 지원하지 않는 토큰입니다.", e);
        } catch (MalformedJwtException e) {
            log.error("토큰 형식 오류", e);
            throw new IllegalArgumentException("[ERROR] 토큰 형식 오류입니다.", e);
        } catch (SignatureException e) {
            log.error("유효하지 않은 토큰 서명", e);
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 토큰 서명입니다.", e);
        }
    }

    private void validTokenPrefix(String token) {
        if (!token.startsWith(TOKEN_PREFIX)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 토큰입니다.");
        }
    }

    private String extractEmail(String token) {
        String subToken = token.substring(TOKEN_BEGIN_INDEX);
        return Jwts.parser()
                .verifyWith(
                        new SecretKeySpec(
                                jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8),
                                SIG.HS256.key().build().getAlgorithm()
                        )
                )
                .build()
                .parseSignedClaims(subToken)
                .getPayload()
                .get(TOKEN_SIGN_CLAIM, String.class);
    }
}
