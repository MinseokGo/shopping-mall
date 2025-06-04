package develop.member.command.application;

import develop.member.command.domain.Member;
import develop.member.command.domain.MemberFactory;
import develop.member.command.domain.MemberRepository;
import develop.member.command.domain.MemberValidator;
import develop.member.command.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

    private final MemberFactory memberFactory;
    private final MemberValidator memberValidator;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveMember(SignUpRequest request) {
        memberValidator.validate(request.email());
        Member member = memberFactory.from(request);
        memberRepository.save(member);
    }
}
