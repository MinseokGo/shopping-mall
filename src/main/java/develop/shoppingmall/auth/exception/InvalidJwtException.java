package develop.shoppingmall.auth.exception;

import org.springframework.http.HttpStatus;

public class InvalidJwtException extends RuntimeException {

    public InvalidJwtException(String message) {
        super(message);
    }

    HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
