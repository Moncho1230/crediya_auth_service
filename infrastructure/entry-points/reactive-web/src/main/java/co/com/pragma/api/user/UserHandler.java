package co.com.pragma.api.user;

import co.com.pragma.api.helpers.RequestValidator;
import co.com.pragma.api.helpers.ResponseMapper;
import co.com.pragma.api.user.dto.CreateUserRequestDto;
import co.com.pragma.api.user.dto.ExistsByDocumentAndEmailDto;
import co.com.pragma.api.user.dto.UserResponseDto;
import co.com.pragma.api.user.mapper.UserDtoMapper;
import co.com.pragma.model.shared.exceptions.BusinessErrorCode;
import co.com.pragma.model.shared.exceptions.LogMessage;
import co.com.pragma.model.shared.exceptions.ResponseCode;
import co.com.pragma.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserHandler {
    private final UserUseCase userUseCase;
    private final UserDtoMapper userDtoMapper;
    private final RequestValidator requestValidator;

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        log.info(LogMessage.SAVE_USER.getMessage());

        return serverRequest.bodyToMono(CreateUserRequestDto.class)
                .flatMap(requestValidator::validator)
                .flatMap(createUserRequestDto -> (
                        userUseCase.save(
                                userDtoMapper.toUser(createUserRequestDto),
                                createUserRequestDto.role()))
                        .map(userDtoMapper::toUserResponseDto)
                        .map(data -> ResponseMapper.mapBodyResponse(ResponseCode.USER_CREATED, data))
                        .flatMap(response ->
                                ServerResponse.status(HttpStatus.CREATED)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(response)
                        ));

    }

    public Mono<ServerResponse> existsByDocumentAndEmail(ServerRequest serverRequest) {
        log.info(LogMessage.EXIST_BY_EMAIL.getMessage());

        return serverRequest.bodyToMono(ExistsByDocumentAndEmailDto.class)
                .flatMap(body -> userUseCase.existsByEmailAndDocument(body.document(), body.email()))
                .map(data -> ResponseMapper.mapBodyResponse(data ?
                                ResponseCode.USER_EXISTS: BusinessErrorCode.USER_DOES_NOT_EXIST
                        , data))
                .flatMap(response ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(response));

    }

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        log.info(LogMessage.GET_ALL_ROLES.getMessage());
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(userUseCase.findAll().map(userDtoMapper::toUserResponseDto), UserResponseDto.class);
    }

}