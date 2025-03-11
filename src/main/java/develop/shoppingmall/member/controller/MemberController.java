package develop.shoppingmall.member.controller;

import develop.shoppingmall.auth.service.AuthService;
import develop.shoppingmall.member.controller.dto.JoinMemberRequest;
import develop.shoppingmall.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

	private final AuthService authService;
	private final MemberService memberService;

	MemberController(MemberService memberService, AuthService authService) {
		this.authService = authService;
		this.memberService = memberService;
	}

	@PostMapping("/members")
	ResponseEntity<String> join(@Valid @RequestBody JoinMemberRequest request) {
		memberService.join(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("회원가입이 완료 되었습니다.");
	}

	@DeleteMapping("/members")
	ResponseEntity<String> deleteMember(HttpServletRequest request) {
		String email = authService.authenticate(request);
		memberService.delete(email);
		return ResponseEntity.ok()
				.body("회원 탈퇴가 정상적으로 처리되었습니다.");
	}
}
