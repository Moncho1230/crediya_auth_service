package co.com.pragma.model.shared.exceptions;
import lombok.Getter;

@Getter
public enum ResponseCode implements ResponseMessage{
    USER_CREATED("USR_001","User created successfully"),
    USER_ALREADY_EXISTS("USR_002","User already exists"),
    USER_EXISTS("USR_003","User exists"),
    TOKEN_GENERATED("AUT_001","Token generated successfully"),
    CORRECT_EMAIL("EML_001","The email is correct"),
    ROLE_FOUND("ROL_001","Role found successfully");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}