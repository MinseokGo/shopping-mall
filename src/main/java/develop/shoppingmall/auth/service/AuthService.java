package develop.shoppingmall.auth.service;

import static develop.shoppingmall.common.utils.JwtConstants.TOKEN_HEADER_KEY;
import develop.shoppingmall.auth.controller.dto.LoginMemberRequest;
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
        String jwt = request.getHeader(TOKEN_HEADER_KEY);
        return jwtService.parse(jwt);
    }
}
