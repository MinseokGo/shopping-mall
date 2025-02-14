package develop.shoppingmall.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class MemberService {

    private final MemberRepository memberRepository;

    MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    void join(JoinMemberRequest request) {
        validateAlreadyExistEmail(request);
        memberRepository.save(create(request));
    }

    private void validateAlreadyExistEmail(JoinMemberRequest request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new AlreadyExistMemberEmailException();
        }
    }

    private Member create(JoinMemberRequest request) {
        return new Member(
                request.name(),
                request.email(),
                request.password()
        );
    }
}
