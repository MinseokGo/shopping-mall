package develop.member.api;

import develop.member.command.application.MemberCommandService;
import develop.member.command.dto.SignUpRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
class MemberCommandController {

    private final MemberCommandService memberService;

    @PostMapping
    ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest request) {
        memberService.saveMember(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("회원가입에 성공하였습니다.");
    }
}
