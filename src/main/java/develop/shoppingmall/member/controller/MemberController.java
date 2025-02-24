package develop.shoppingmall.member.controller;

import develop.shoppingmall.member.controller.dto.JoinMemberRequest;
import develop.shoppingmall.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

	private final MemberService memberService;

	MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping("/members")
	ResponseEntity<String> join(@Valid @RequestBody JoinMemberRequest request) {
		memberService.join(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("회원가입이 완료 되었습니다.");
	}
}
