package develop.shoppingmall.auth.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(JwtExceptionHandler.class);

    @ExceptionHandler(InvalidJwtException.class)
    ResponseEntity<String> invalidJwtException(InvalidJwtException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(exception.getStatus())
                .body(exception.getMessage());
    }
}
