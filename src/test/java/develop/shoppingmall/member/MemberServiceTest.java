package develop.shoppingmall.member;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member member = new Member("testName", "testEmail", "testPassword");
        memberRepository.save(member);
    }

    @Test
    @DisplayName("실패 - 이미 존재하는 이메일로 회원가입 시도 시 예외 처리 테스트")
    void test0() {
        JoinMemberRequest request = new JoinMemberRequest("testName", "testEmail", "testPassword");
        assertThatThrownBy(() -> memberService.join(request))
                .isInstanceOf(AlreadyExistMemberEmailException.class)
                .hasMessage("[ERROR] 이미 존재하는 이메일입니다.");
    }

    @Test
    @DisplayName("회원가입 성공 테스트")
    void test1() {
        JoinMemberRequest request = new JoinMemberRequest("testName", "newEmail", "testPassword");
        memberService.join(request);
    }

    @Test
    @DisplayName("멤버 로그인 테스트")
    void test2() {
        LoginMemberRequest request = new LoginMemberRequest("testEmail", "testPassword");
        memberService.login(request);
    }

    @Test
    @DisplayName("실패 - 멤버 로그인 테스트")
    void test3() {
        LoginMemberRequest request = new LoginMemberRequest("testEmail", "incorrectPassword");
        assertThatThrownBy(() -> memberService.login(request))
                .isInstanceOf(LoginFailedException.class)
                .hasMessage("[ERROR] 로그인에 실패 하였습니다.");
    }
}