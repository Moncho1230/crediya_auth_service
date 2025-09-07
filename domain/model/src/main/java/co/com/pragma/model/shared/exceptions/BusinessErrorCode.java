package co.com.pragma.model.shared.exceptions;
import lombok.Getter;

@Getter
public enum BusinessErrorCode implements ResponseMessage{
    ROLE_DOES_NOT_EXIST("E404","Role does not exist"),
    USER_DOES_NOT_EXIST("E404","User does not exist"),
    USER_ALREADY_EXISTS("E400-01","User already exists"),
    VALIDATION_FAILED("E400", "Validation failed"),
    ACCESS_DENIED("A403","Access denied"),
    INVALID_CREDENTIALS("A401","Invalid credentials"),
    INTERNAL_SERVER_ERROR("S500","An internal server error occurred"),
    INVALID_EMAIL("E400-02","The email is invalid");

    private final String code;
    private final String message;

    BusinessErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}