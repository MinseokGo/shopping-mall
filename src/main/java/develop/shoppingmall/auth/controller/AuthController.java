package develop.shoppingmall.auth.controller;

import static develop.shoppingmall.common.utils.JwtConstants.TOKEN_HEADER_KEY;
import develop.shoppingmall.auth.service.AuthService;
import develop.shoppingmall.auth.controller.dto.LoginMemberRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    ResponseEntity<String> signIn(@Valid @RequestBody LoginMemberRequest request) {
        String jwt = authService.signIn(request);
        return ResponseEntity.ok()
                .header(TOKEN_HEADER_KEY, jwt)
                .body("로그인에 성공 하였습니다.");
    }
}
