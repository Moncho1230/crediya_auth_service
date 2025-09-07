package co.com.pragma.model.shared;
import lombok.Getter;

@Getter
public enum Roles {
    ADMIN("administrador"),
    CLIENT("cliente"),
    ADVISER("asesor");

    private final String value;

    Roles(String value) {
        this.value = value;
    }
}
