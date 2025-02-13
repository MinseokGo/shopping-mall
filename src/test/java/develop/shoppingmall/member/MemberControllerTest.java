package develop.shoppingmall.member;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class MemberControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    MemberController memberController;

    @Test
    @DisplayName("멤버 회원가입 통합 테스트")
    void test0() {
        JoinMemberRequest request = new JoinMemberRequest("testName", "testEmail@email.com", "testPassword");
        given()
                .baseUri("http://localhost:" + port)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/members")
                .then()
                .statusCode(201)
                .body(equalTo("회원가입이 완료 되었습니다."));
    }
}