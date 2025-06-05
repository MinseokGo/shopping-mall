package develop.auth.api;

import develop.auth.application.SignInService;
import develop.auth.dto.SignInRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final SignInService signInService;

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@Valid @RequestBody SignInRequest request) {
        String jwt = signInService.signIn(request);
        return ResponseEntity.ok()
                .header("Authorization", jwt)
                .body("로그인에 성공하였습니다.");
    }
}
