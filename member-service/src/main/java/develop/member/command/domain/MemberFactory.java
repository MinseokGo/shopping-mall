package develop.member.command.domain;

import develop.member.command.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFactory {

    private final PasswordEncoder passwordEncoder;

    public Member from(SignUpRequest request) {
        return Member.create(
                request.name(),
                request.email(),
                passwordEncoder.encode(request.password())
        );
    }
}
