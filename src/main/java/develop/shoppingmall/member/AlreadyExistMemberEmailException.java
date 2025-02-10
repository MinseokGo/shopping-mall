package develop.shoppingmall.member;

import org.springframework.http.HttpStatus;

class AlreadyExistMemberEmailException extends RuntimeException {

    private static final String ALREADY_EXIST_MEMBER_EMAIL_EXCEPTION_MESSAGE = "[ERROR] 이미 존재하는 이메일입니다.";

    AlreadyExistMemberEmailException() {
        super(ALREADY_EXIST_MEMBER_EMAIL_EXCEPTION_MESSAGE);
    }

    HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
