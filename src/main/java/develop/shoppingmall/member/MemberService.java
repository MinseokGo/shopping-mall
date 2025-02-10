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
        if (memberRepository.existsByEmail(request.email())) {
            throw new AlreadyExistMemberEmailException();
        }
        memberRepository.save(createNewMember(request));
    }

    private Member createNewMember(JoinMemberRequest request) {
        return new Member(
                request.name(),
                request.email(),
                request.password()
        );
    }
}
