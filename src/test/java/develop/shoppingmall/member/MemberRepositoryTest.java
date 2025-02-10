package develop.shoppingmall.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTest {

    MemberRepository memberRepository;

    @Autowired
    MemberRepositoryTest(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Test
    @DisplayName("이미 존재하는 이메일 검증 테스트")
    void test0() {
        Member member = new Member("testName", "testEmail", "testPassword");
        memberRepository.save(member);

        assertThat(
                memberRepository.existsByEmail(member.getEmail())
        ).isTrue();
    }
}