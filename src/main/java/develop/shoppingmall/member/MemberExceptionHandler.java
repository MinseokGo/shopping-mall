package develop.shoppingmall.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class MemberExceptionHandler {

    @ExceptionHandler(AlreadyExistMemberEmailException.class)
    ResponseEntity<String> alreadyExistMemberEmailException(AlreadyExistMemberEmailException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(exception.getMessage());
    }

    @ExceptionHandler(LoginFailedException.class)
    ResponseEntity<String> loginFailedException(LoginFailedException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(exception.getMessage());
    }
}
