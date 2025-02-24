package develop.shoppingmall.auth;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class JwtServiceTest {

    @Autowired
    JwtService jwtService;

    @Test
    @DisplayName("JWT 생성 테스트")
    void test0() {
        assertThat(
                jwtService.create("testEmail")
        ).contains("Bearer ");
    }
}
