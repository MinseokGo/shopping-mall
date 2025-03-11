package develop.shoppingmall.member.repository;

import static org.assertj.core.api.Assertions.assertThat;

import develop.shoppingmall.common.config.QueryDSLConfig;
import develop.shoppingmall.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Import(QueryDSLConfig.class)
@DataJpaTest
@ActiveProfiles("test")
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member member = new Member("testName", "testEmail", "testPassword");
        memberRepository.save(member);
    }

    @Test
    @DisplayName("이미 존재하는 이메일 검증 테스트")
    void test0() {
        assertThat(
                memberRepository.existsByEmail("testEmail")
        ).isTrue();
    }

    @Test
    @DisplayName("멤버의 비밀번호만 조회하는 쿼리 메서드 테스트")
    void test1() {
        String email = "testEmail";
        assertThat(
                memberRepository.findPassword(email).password()
        ).isEqualTo("testPassword");
    }

    @Test
    @DisplayName("멤버 탈퇴 시 멤버 활성 상태 변경 쿼리 메서드 테스트")
    void test2() {
        String email = "testEmail";
        assertThat(
                memberRepository.deleteByEmail(email)
        ).isEqualTo(1);
    }

    @Test
    @DisplayName("실패 - 멤버 탈퇴 시 존재하지 않는 이메일로 탈퇴 시도")
    void test3() {
        String email = "notExistEmail";
        assertThat(
                memberRepository.deleteByEmail(email)
        ).isEqualTo(0);
    }
}