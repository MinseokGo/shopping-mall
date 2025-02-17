package develop.shoppingmall.member.controller;

import develop.shoppingmall.member.JoinMemberRequest;
import develop.shoppingmall.member.LoginMemberRequest;
import develop.shoppingmall.member.MemberService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MemberController {

	private final MemberService memberService;

	MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/members")
	ResponseEntity<String> login(
			@Valid @RequestBody LoginMemberRequest request
	) {
		memberService.login(request);
		return ResponseEntity.ok()
				.body("로그인에 성공 하였습니다.");
	}

	@PostMapping("/members")
	ResponseEntity<String> join(
		@Valid @RequestBody JoinMemberRequest request
	) {
		memberService.join(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("회원가입이 완료 되었습니다.");
	}
}
