package develop.member.query.application;

import develop.common.dto.SignInRequestMessage;
import develop.common.dto.SignInResponseMessage;
import develop.member.query.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public SignInResponseMessage isPossibleLogin(SignInRequestMessage message) {
        String password = memberRepository.getPassword(message.email());
        if (!StringUtils.hasText(password) ||
                !passwordEncoder.matches(message.password(), password)) {
            return new SignInResponseMessage(message.requestId(), false);
        }
        return new SignInResponseMessage(message.requestId(), true);
    }
}
