package develop.shoppingmall.auth.service;

import develop.shoppingmall.auth.service.dto.JwtProperties;
import static develop.shoppingmall.common.utils.JwtConstants.HOURS;
import static develop.shoppingmall.common.utils.JwtConstants.MILLISECONDS;
import static develop.shoppingmall.common.utils.JwtConstants.MINUTES;
import static develop.shoppingmall.common.utils.JwtConstants.SECONDS;
import static develop.shoppingmall.common.utils.JwtConstants.SHOPPING_MALL_AUTH_SUBJECT;
import static develop.shoppingmall.common.utils.JwtConstants.TOKEN_BEGIN_INDEX;
import static develop.shoppingmall.common.utils.JwtConstants.TOKEN_PREFIX;
import static develop.shoppingmall.common.utils.JwtConstants.TOKEN_PROVIDER;
import static develop.shoppingmall.common.utils.JwtConstants.TOKEN_SIGN_CLAIM;
import develop.shoppingmall.auth.exception.InvalidJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class JwtService {

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
            throw new InvalidJwtException("[ERROR] 만료된 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new InvalidJwtException("[ERROR] 지원하지 않는 토큰입니다.");
        } catch (MalformedJwtException e) {
            throw new InvalidJwtException("[ERROR] 토큰 형식 오류입니다.");
        } catch (SignatureException e) {
            throw new InvalidJwtException("[ERROR] 유효하지 않은 토큰 서명입니다.");
        }
    }

    private void validTokenPrefix(String token) {
        if (!StringUtils.hasText(token) || !token.startsWith(TOKEN_PREFIX)) {
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
