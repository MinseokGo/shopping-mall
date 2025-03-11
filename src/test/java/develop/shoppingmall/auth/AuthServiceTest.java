package develop.shoppingmall.auth;

import develop.shoppingmall.auth.controller.dto.LoginMemberRequest;
import develop.shoppingmall.auth.service.AuthService;
import develop.shoppingmall.member.domain.Member;
import develop.shoppingmall.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member member = new Member("testName", "testEmail", "testPassword");
        memberRepository.save(member);
    }

    @Test
    @DisplayName("로그인 테스트")
    void test0() {
        LoginMemberRequest request = new LoginMemberRequest("testEmail", "testPassword");
        authService.signIn(request);
    }
}