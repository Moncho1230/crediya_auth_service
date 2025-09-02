package co.com.pragma.model.shared.exceptions;

public class ValidationException extends DomainException {
    public ValidationException(String message) {
        super(ErrorCode.INVALID_USER_DATA, message);
    }

    public static ValidationException userRequired() {
        return new ValidationException("User is required");
    }

    public static ValidationException invalidEmail() {
        return new ValidationException("User email is not valid");
    }

    public static ValidationException negativeBalance() {
        return new ValidationException("User has negative balance");
    }

    public static ValidationException maxBalanceExceeded() {
        return new ValidationException("User balance exceeds maximum allowed");
    }
}
