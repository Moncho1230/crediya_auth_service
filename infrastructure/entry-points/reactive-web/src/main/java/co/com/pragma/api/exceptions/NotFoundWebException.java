package co.com.pragma.api.exception;

public class NotFoundWebException extends WebException {
    public NotFoundWebException(String messageKey, Object... args) {
        super(ErrorCode.NOT_FOUND, "Resource not found", args);
    }
}
