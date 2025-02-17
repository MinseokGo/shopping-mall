package develop.shoppingmall.member.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistMemberEmailException extends RuntimeException {

    private static final String ALREADY_EXIST_MEMBER_EMAIL_EXCEPTION_MESSAGE = "[ERROR] 이미 존재하는 이메일입니다.";

    public AlreadyExistMemberEmailException() {
        super(ALREADY_EXIST_MEMBER_EMAIL_EXCEPTION_MESSAGE);
    }

    HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
