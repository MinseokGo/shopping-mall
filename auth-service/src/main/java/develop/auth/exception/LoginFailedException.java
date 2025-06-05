package develop.auth.exception;

import develop.common.exception.BaseException;
import develop.common.exception.ErrorCode;

public class LoginFailedException extends BaseException {

    public LoginFailedException() {
        super(ErrorCode.LOGIN_FAILED);
    }

    public LoginFailedException(ErrorCode code) {
        super(code);
    }
}
