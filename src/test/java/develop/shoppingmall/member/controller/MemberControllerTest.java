package develop.shoppingmall.member.controller;

import develop.shoppingmall.auth.service.AuthService;
import develop.shoppingmall.auth.controller.dto.LoginMemberRequest;
import develop.shoppingmall.member.controller.dto.JoinMemberRequest;
import develop.shoppingmall.member.repository.MemberRepository;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.BeforeEach;
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
    EntityManagerFactory entityManagerFactory;

    @Autowired
    AuthService authService;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        try {
            memberRepository.deleteAll();
            entityManager.flush();
            entityManager.clear();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

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

    @Test
    @DisplayName("실패 - 멤버 회원가입 통합 테스트")
    void test1() {
        join();

        JoinMemberRequest newRequest = new JoinMemberRequest("testName", "testEmail@email.com", "testPassword");
        given()
                .baseUri("http://localhost:" + port)
                .contentType(ContentType.JSON)
                .body(newRequest)
                .when()
                .post("/members")
                .then()
                .statusCode(409)
                .body(equalTo("[ERROR] 이미 존재하는 이메일입니다."));
    }

    @Test
    @DisplayName("멤버 탈퇴 통합 테스트")
    void test2() {
        join();

        String jwt = authService.signIn(new LoginMemberRequest("testEmail@email.com", "testPassword"));
        given()
                .baseUri("http://localhost:" + port)
                .contentType(ContentType.JSON)
                .header("Authorization", jwt)
                .when()
                .then()
                .statusCode(200)
                .body(equalTo("회원 탈퇴가 정상적으로 처리되었습니다."));
    }

    void join() {
        JoinMemberRequest oldRequest = new JoinMemberRequest("testName", "testEmail@email.com", "testPassword");
        given()
                .baseUri("http://localhost:" + port)
                .contentType(ContentType.JSON)
                .body(oldRequest)
                .when()
                .post("/members")
                .then()
                .statusCode(201)
                .body(equalTo("회원가입이 완료 되었습니다."));
    }
}