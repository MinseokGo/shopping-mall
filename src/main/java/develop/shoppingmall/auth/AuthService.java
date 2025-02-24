package develop.shoppingmall.auth;

import develop.shoppingmall.auth.dto.LoginMemberRequest;
import develop.shoppingmall.member.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final MemberService memberService;

    AuthService(JwtService jwtService, MemberService memberService) {
        this.jwtService = jwtService;
        this.memberService = memberService;
    }

    public String signIn(LoginMemberRequest request) {
        memberService.authenticate(request.email(), request.password());
        return jwtService.create(request.email());
    }
}
