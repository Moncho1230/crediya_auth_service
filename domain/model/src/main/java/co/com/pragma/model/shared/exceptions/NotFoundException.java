package co.com.pragma.model.shared.exceptions;

public class NotFoundException extends DomainException {
    public NotFoundException(ErrorCode code, String message) {
        super(code, message);

    }

    public static NotFoundException user(String email){
        return new NotFoundException(ErrorCode.USER_DOES_NOT_EXIST,
                "Este usuario no esxiste: "+ email);
    }


}
