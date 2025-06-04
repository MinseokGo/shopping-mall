package develop.common.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        int code,
        String message,
        String type,
        LocalDateTime timestamp
) {

    public static ErrorResponse of(ErrorCode code, String type) {
        return new ErrorResponse(
                code.getStatusCode(),
                code.getMessage(),
                type,
                LocalDateTime.now()
        );
    }
}
