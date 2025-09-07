package co.com.pragma.usecase.user;

import co.com.pragma.model.shared.exceptions.AlreadyExistsException;
import co.com.pragma.model.shared.exceptions.ValidationException;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.model.shared.gateways.TransacionalGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

/**
 * Caso de uso para la lógica de negocio relacionada con usuarios.
 * Proporciona métodos para la creación, validación y consulta de usuarios de forma reactiva y transaccional.
 */
@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;
    private final TransacionalGateway transacionalGateway;
    String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    /**
     * Crea un nuevo usuario después de validar y verificar su existencia.
     * La operación se realiza de forma transaccional y no bloqueante.
     *
     * @param user el usuario a crear
     * @return un Mono que emite el usuario creado o un error si la validación falla o el usuario ya existe
     */
    public Mono<User> createUser(User user) {
        return validate(user)
            .then(userRepository.existsByEmail(user.getEmail()))
            .flatMap(exists -> exists
                ? Mono.error(AlreadyExistsException.email(user.getEmail()))
                : transacionalGateway.executeInTransaction(userRepository.save(user))
            );
    }


    /**
     * Valida el objeto usuario para campos requeridos, formato de email y restricciones de balance.
     *
     * @param user el usuario a validar
     * @return un Mono que completa si es válido o emite una ValidationException
     */
    public Mono<Void> validate(User user) {
        return Mono.justOrEmpty(user)
            .switchIfEmpty(Mono.error(ValidationException.userRequired()))
            .flatMap(u -> Pattern.matches(emailRegex, u.getEmail())
                ? Mono.just(u)
                : Mono.error(ValidationException.invalidEmail()))
                .flatMap(u -> u.getBalance() == null
                        ? Mono.error(ValidationException.negativeBalance())
                        : Mono.just(u))
            .flatMap(u -> u.getBalance() < 0
                ? Mono.error(ValidationException.negativeBalance())
                : Mono.just(u))
            .flatMap(u -> u.getBalance() > 15000000
                ? Mono.error(ValidationException.maxBalanceExceeded())
                : Mono.just(u))
            .then();
    }
}
