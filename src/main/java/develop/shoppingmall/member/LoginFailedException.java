package develop.shoppingmall.member;

import org.springframework.http.HttpStatus;

class LoginFailedException extends RuntimeException {

    private static final String LOGIN_FAILED_EXCEPTION_MESSAGE = "[ERROR] 로그인에 실패 하였습니다.";

    LoginFailedException() {
        super(LOGIN_FAILED_EXCEPTION_MESSAGE);
    }

    HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
