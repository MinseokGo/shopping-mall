package develop.shoppingmall.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

record JoinMemberRequest(

        @NotBlank(message = "이름을 입력해주세요.")
        @Size(min = 2, max = 16, message = "이름은 2자 이상 16자 이하여야 합니다.")
        String name,

        @NotBlank(message = "이메일을 입력해주세요.")
        @Size(min = 2, max = 128, message = "이메일은 2자 이상 128자 이하여야 합니다.")
        @Email(message = "이메일 형식으로 입력해주세요.")
        String email,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Size(min = 8, max = 512, message = "비밀번호는 8자 이상 512자 이하여야 합니다.")
        String password
) {
}
