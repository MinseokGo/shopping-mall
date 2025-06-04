package develop.member.command.domain.exception;

import develop.common.exception.BaseException;
import develop.common.exception.ErrorCode;

public class DuplicateEmailException extends BaseException {

    public DuplicateEmailException() {
        super(ErrorCode.DUPLICATE_EMAIL);
    }
}
