package develop.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "잘못된 입력입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러가 발생했습니다.")
    ;

    private final HttpStatus code;
    private final String message;

    ErrorCode(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getStatusCode() {
        return code.value();
    }
}
