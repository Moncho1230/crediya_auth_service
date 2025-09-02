package co.com.pragma.model.shared.exceptions;


public abstract class DomainException extends RuntimeException {
    private final ErrorCode code;

    protected DomainException(ErrorCode code, String message) {
        super(message);
        this.code = code;

    }

    public ErrorCode getCode() {
        return code;
    }
}

