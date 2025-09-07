package co.com.pragma.model.user.gateways;

public interface PasswordCryptoGateway {
    String encode(String password);
    Boolean matches(String encodedPassword, String rawPassword);
}
