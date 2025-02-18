package develop.shoppingmall.member.exception;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends RuntimeException {

    private static final String MEMBER_NOT_FOUND_EXCEPTION_MESSAGE = "[ERROR] 멤버 정보를 찾을 수 없습니다.";

    public MemberNotFoundException() {
        super(MEMBER_NOT_FOUND_EXCEPTION_MESSAGE);
    }

    HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
