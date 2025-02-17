package develop.shoppingmall.member.exception;

import org.springframework.http.HttpStatus;

public class LoginFailedException extends RuntimeException {

    private static final String LOGIN_FAILED_EXCEPTION_MESSAGE = "[ERROR] 로그인에 실패 하였습니다.";

    public LoginFailedException() {
        super(LOGIN_FAILED_EXCEPTION_MESSAGE);
    }

    HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
