package develop.shoppingmall.auth;

import develop.shoppingmall.auth.controller.dto.LoginMemberRequest;
import develop.shoppingmall.member.domain.Member;
import develop.shoppingmall.member.repository.MemberRepository;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AuthControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAllInBatch();
        Member member = new Member("testName", "testEmail", "testPassword");
        memberRepository.save(member);
    }

    @Test
    @DisplayName("로그인 테스트")
    void test0() {
        LoginMemberRequest request = new LoginMemberRequest("testEmail", "testPassword");
        given()
                .baseUri("http://localhost:" + port)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/sign-in")
                .then()
                .statusCode(200)
                .header("Authorization", notNullValue())
                .body(equalTo("로그인에 성공 하였습니다."));
    }

    @Test
    @DisplayName("실패 - 로그인 테스트")
    void test1() {
        LoginMemberRequest request = new LoginMemberRequest("notExistEmail", "testPassword");
        given()
                .baseUri("http://localhost:" + port)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/sign-in")
                .then()
                .statusCode(404)
                .body(equalTo("[ERROR] 멤버 정보를 찾을 수 없습니다."));
    }
}