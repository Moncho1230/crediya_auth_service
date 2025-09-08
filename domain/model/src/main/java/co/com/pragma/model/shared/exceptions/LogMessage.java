package co.com.pragma.model.shared.exceptions;
import lombok.Getter;

@Getter
public enum LogMessage{
    SAVE_USER("Saving user in database"),
    EXIST_BY_DOCUMENT("Checking if user exists by document"),
    EXIST_BY_EMAIL("Checking if user exists by email"),
    FIND_ROLE_BY_NAME("Finding role by name"),
    GET_ALL_ROLES("Getting all roles"),
    LOGIN_SUCCESS("Login successful"),
    IS_VALID_EMAIL("Checking if email is valid");



    private final String message;

    LogMessage(String message) {
        this.message = message;
    }
}