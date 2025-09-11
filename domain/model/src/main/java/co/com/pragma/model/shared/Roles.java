package co.com.pragma.model.shared;
import lombok.Getter;

@Getter
public enum Roles {
    ADMIN("Administrador"),
    CLIENT("Cliente"),
    ADVISER("Asesor");

    private final String value;

    Roles(String value) {
        this.value = value;
    }
}
