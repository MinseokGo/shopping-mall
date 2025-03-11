package develop.shoppingmall.auth;

import develop.shoppingmall.auth.service.JwtService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class JwtServiceTest {

    String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNaW5zZW9rIEdvIiwiaXNzIjoiTWluc2VvayBHbyBzaG9wcGluZyBtYWxsIHNlcnZlciIsImp0aSI6InRlc3RFbWFpbCIsImVtYWlsIjoidGVzdEVtYWlsIiwiaWF0IjoxNzQwNDAwOTEzLCJleHAiOjE3NDA0ODczMTN9.pOjwkGWqA0MDbN0_cHx-KZN_QRb_9CrcBBdJSFK5b_g";

    @Autowired
    JwtService jwtService;

    @Test
    @DisplayName("JWT 생성 테스트")
    void test0() {
        assertThat(
                jwtService.create("testEmail")
        ).contains("Bearer ");
    }

    @Test
    @DisplayName("JWT 파싱 테스트")
    void test1() {
        String token = jwtService.create("testEmail");
        jwtService.parse(token);
    }

    @Test
    @DisplayName("실패 - 유효하지 않은 JWT")
    void test2() {
        assertThatThrownBy(() -> jwtService.parse(token.substring(1)))
                .hasMessage("[ERROR] 유효하지 않은 토큰입니다.");
    }
}
