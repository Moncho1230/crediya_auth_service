package co.com.pragma.model.shared.exceptions;

public class AlreadyExistsException extends DomainException {
    public AlreadyExistsException(ErrorCode code, String message) {
        super(code, message);
    }

    public static AlreadyExistsException email(String email) {
        return new AlreadyExistsException(
                ErrorCode.USER_ALREADY_EXISTS,
                "Este usuario ya existe: "+ email
        );

    }
}
