package co.com.pragma.api.auth;

import lombok.Getter;

@Getter
public enum TokenClaims {
    EMAIL("email"),
    ROLE("role"),
    DOCUMENT("document");
    private final String value;

    TokenClaims(String value){
        this.value = value;
    }
}
