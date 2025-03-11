package develop.shoppingmall.auth;

import develop.shoppingmall.auth.dto.LoginMemberRequest;
import develop.shoppingmall.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
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
        memberService.findByEmail(request.email(), request.password());
        return jwtService.create(request.email());
    }

    public String authenticate(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        return jwtService.parse(jwt);
    }
}
