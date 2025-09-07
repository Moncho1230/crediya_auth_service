package co.com.pragma.usecase.user;

import co.com.pragma.model.exceptions.BusinessException;
import co.com.pragma.model.role.gateways.RoleRepository;
import co.com.pragma.model.shared.exceptions.BusinessErrorCode;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.PasswordCryptoGateway;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.model.shared.gateways.TransacionalGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


/**
 * Caso de uso para la lógica de negocio relacionada con usuarios.
 * Proporciona métodos para la creación, validación y consulta de usuarios de forma reactiva y transaccional.
 */
@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TransacionalGateway transacionalGateway;
    private final PasswordCryptoGateway passwordCryptoGateway;


    public Mono<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorCode.USER_DOES_NOT_EXIST)));
    }

    public Mono<String> findRoleNameByEmail(String email) {
        return userRepository.findRoleNameByEmail(email)
                .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorCode.USER_DOES_NOT_EXIST)));
    }

    public Mono<Boolean> existsByEmailAndDocument(String email, String document) {
        return userRepository.existsByEmailAndDocument(email, document)
                .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorCode.USER_DOES_NOT_EXIST)));
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> validateCredentials(String email, String password) {
        return this.findByEmail(email)
                .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorCode.USER_DOES_NOT_EXIST)))
                .filter(user -> passwordCryptoGateway.matches(password, user.getPassword()))
                .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorCode.INVALID_CREDENTIALS)));

    }

    /**
     * Crea un nuevo usuario después de validar y verificar su existencia.
     * La operación se realiza de forma transaccional y no bloqueante.
     *
     * @param user el usuario a crear
     * @return un Mono que emite el usuario creado o un error si la validación falla o el usuario ya existe
     */
    public Mono<User> save(User user, String role) {
        return userRepository.existsByEmailOrDocument(user.getEmail(), user.getDocument())
                .filter(exists -> !exists)
                .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorCode.USER_ALREADY_EXISTS)))
                .then(roleRepository.findByName(role)
                        .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorCode.ROLE_DOES_NOT_EXIST))))
                .map(roleRetrieved -> user.toBuilder()
                        .roleId(roleRetrieved.getRoleId())
                        .password(passwordCryptoGateway.encode(user.getPassword()))
                        .build())
                .flatMap(u -> transacionalGateway.executeInTransaction(userRepository.save(u)));
    }

}
