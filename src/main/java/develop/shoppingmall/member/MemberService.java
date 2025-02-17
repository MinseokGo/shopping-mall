package develop.shoppingmall.member;

import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
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

    void login(LoginMemberRequest request) {
        FindLoginMemberDTO passwordDTO = memberRepository.findPassword(request.email());
        validateCorrectPassword(request, passwordDTO);
    }

    private void validateCorrectPassword(LoginMemberRequest request, FindLoginMemberDTO passwordDTO) {
        if (!Objects.equals(passwordDTO.password(), request.password())) {
            throw new LoginFailedException();
        }
    }
}
