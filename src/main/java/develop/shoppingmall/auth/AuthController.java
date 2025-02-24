package develop.shoppingmall.auth;

import develop.shoppingmall.auth.dto.LoginMemberRequest;
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
                .header("Authorization", jwt)
                .body("로그인에 성공 하였습니다.");
    }
}
