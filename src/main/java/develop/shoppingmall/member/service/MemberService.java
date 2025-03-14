package develop.shoppingmall.member.service;

import develop.shoppingmall.member.controller.dto.JoinMemberRequest;
import develop.shoppingmall.member.domain.Member;
import develop.shoppingmall.member.exception.AlreadyExistMemberEmailException;
import develop.shoppingmall.member.exception.LoginFailedException;
import develop.shoppingmall.member.repository.MemberRepository;
import develop.shoppingmall.member.service.dto.FindLoginMemberDTO;
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

    public void findByEmail(String email, String password) {
        FindLoginMemberDTO passwordDTO = memberRepository.findPassword(email);
        validateCorrectPassword(password, passwordDTO);
    }

    private void validateCorrectPassword(String password, FindLoginMemberDTO passwordDTO) {
        if (!Objects.equals(passwordDTO.password(), password)) {
            throw new LoginFailedException();
        }
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

    @Transactional
    public void delete(String email) {
        memberRepository.deleteByEmail(email);
    }
}
