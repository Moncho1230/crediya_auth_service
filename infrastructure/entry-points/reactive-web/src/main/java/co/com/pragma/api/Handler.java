package co.com.pragma.api;

import co.com.pragma.api.dto.UserRequestDto;
import co.com.pragma.api.exceptions.NotFoundWebException;
import co.com.pragma.api.exceptions.WebException;
import co.com.pragma.api.mapper.UserDtoMapper;
import co.com.pragma.usecase.user.UserUseCase;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
/**
 * Clase Handler para gestionar solicitudes HTTP relacionadas con usuarios en la capa web reactiva.
 * Proporciona endpoints para el registro y la consulta de usuarios.
 */
public class Handler {
private  final UserUseCase useCase;
private final UserDtoMapper mapper;
private final Validator validator;

    /**
     * Maneja el registro de un nuevo usuario.
     * <p>
     * Espera un {@link UserRequestDto} en el cuerpo de la solicitud, lo valida, lo mapea a un usuario de dominio
     * y delega la creación al caso de uso. Retorna una respuesta JSON con los datos del usuario creado o un error.
     *
     * @param serverRequest la solicitud entrante que contiene los datos del usuario
     * @return un {@link Mono} que emite la respuesta del servidor con el usuario creado o detalles del error
     */
    public Mono<ServerResponse> registerUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserRequestDto.class)
                .switchIfEmpty(Mono.error(new NotFoundWebException("user.not_found")))
                .doOnNext(this::validate)
                .map(mapper::toUser)
                .flatMap(useCase::createUser)
                .map(mapper::toResponse)
                .flatMap(dto -> ServerResponse.created(URI.create("/api/v1/users" ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(dto));

    }

    /**
     * Consulta todos los usuarios.
     * <p>
     * Retorna una respuesta JSON que contiene la lista de usuarios.
     *
     * @param serverRequest la solicitud entrante
     * @return un {@link Mono} que emite la respuesta del servidor con la lista de usuarios
     */
    public Mono<ServerResponse> getUsers (ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("");
    }

    /**
     * Valida el objeto dado usando el {@link Validator} inyectado.
     * Lanza una excepción de validación si se violan las restricciones.
     *
     * @param o el objeto a validar
     */
    private void validate(Object o) {
        var v = validator.validate(o);

    }
}
