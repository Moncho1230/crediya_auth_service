package co.com.pragma.api.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "validation.error"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "user.not_found"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "internal.error");

    public final HttpStatus status;
    public final String messageKey;

    ErrorCode(HttpStatus status, String messageKey) {
        this.status = status;
        this.messageKey = messageKey;
    }
}
