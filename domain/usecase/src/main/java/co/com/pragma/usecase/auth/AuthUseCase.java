package co.com.pragma.usecase.auth;

import co.com.pragma.model.auth.AuthUser;
import co.com.pragma.model.auth.gateways.AuthRepository;
import co.com.pragma.model.role.gateways.RoleRepository;
import co.com.pragma.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AuthUseCase {

    private final AuthRepository authRepository;
    private final UserUseCase userUseCase;
    private final RoleRepository roleRepository;

    public Mono<String> login(String email, String password) {
        return userUseCase.validateCredentials(email, password)
                .zipWhen(user -> roleRepository.findById(user.getRoleId()))
                .flatMap(tuple -> Mono.just(new AuthUser(tuple.getT1().getDocument(),
                        tuple.getT1().getEmail(), tuple.getT2().getName())))
                .flatMap(authRepository::generateToken);
    }

    public Mono<AuthUser> ValidToken(String token) {
        return authRepository.validateToken(token);
    }

    public Mono<Boolean> isEmailEqualToToken(String email) {
        return authRepository.isEmailEqualToToken(email);
    }
}
