package co.com.pragma.api.exceptions;

public class WebException extends RuntimeException {
    public final ErrorCode code;
    public final Object[] args;

    public WebException(ErrorCode code, String debugMessage, Object... args) {
        super(debugMessage);
        this.code = code;
        this.args = args;
    }
}
