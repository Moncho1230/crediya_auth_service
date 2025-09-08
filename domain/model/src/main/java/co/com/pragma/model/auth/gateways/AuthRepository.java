package co.com.pragma.model.auth.gateways;

import co.com.pragma.model.auth.AuthUser;
import reactor.core.publisher.Mono;

public interface AuthRepository {
    Mono<String> generateToken(AuthUser authUser);
    Mono<AuthUser> validateToken(String token);
    Mono<String> getTokenEmail();
}
