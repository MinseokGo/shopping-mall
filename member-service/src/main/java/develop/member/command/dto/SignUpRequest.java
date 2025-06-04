package develop.member.command.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(

        @NotBlank(message = "이름을 입력해주세요.")
        String name,

        @Email(message = "이메일 형식으로 입력해주세요.")
        @NotBlank(message = "이메일을 입력해주세요.")
        String email,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        String password
) {
}
