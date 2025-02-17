package develop.shoppingmall.member.service;

import develop.shoppingmall.member.FindLoginMemberDTO;
import develop.shoppingmall.member.JoinMemberRequest;
import develop.shoppingmall.member.LoginMemberRequest;
import develop.shoppingmall.member.domain.Member;
import develop.shoppingmall.member.exception.AlreadyExistMemberEmailException;
import develop.shoppingmall.member.exception.LoginFailedException;
import develop.shoppingmall.member.repository.MemberRepository;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void join(JoinMemberRequest request) {
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

    public void login(LoginMemberRequest request) {
        FindLoginMemberDTO passwordDTO = memberRepository.findPassword(request.email());
        validateCorrectPassword(request, passwordDTO);
    }

    private void validateCorrectPassword(LoginMemberRequest request, FindLoginMemberDTO passwordDTO) {
        if (!Objects.equals(passwordDTO.password(), request.password())) {
            throw new LoginFailedException();
        }
    }
}
