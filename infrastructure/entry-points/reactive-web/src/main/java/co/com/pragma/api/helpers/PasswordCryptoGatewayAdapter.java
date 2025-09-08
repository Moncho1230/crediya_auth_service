package co.com.pragma.api.helpers;

import co.com.pragma.model.user.gateways.PasswordCryptoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class PasswordCryptoGatewayAdapter implements PasswordCryptoGateway {

    private final PasswordEncoder encoder;

    @Override
    public String encode(String password) {
        return encoder.encode(password);
    }

    @Override
    public Boolean matches(String encodedPassword, String rawPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
