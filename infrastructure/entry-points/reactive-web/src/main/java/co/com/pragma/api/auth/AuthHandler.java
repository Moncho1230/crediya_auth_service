package co.com.pragma.api.auth;

import co.com.pragma.api.auth.dto.EmailEqualToTokenDto;
import co.com.pragma.api.auth.dto.LoginDto;
import co.com.pragma.api.helpers.RequestValidator;
import co.com.pragma.api.helpers.ResponseMapper;
import co.com.pragma.model.shared.exceptions.BusinessErrorCode;
import co.com.pragma.model.shared.exceptions.LogMessage;
import co.com.pragma.model.shared.exceptions.ResponseCode;
import co.com.pragma.usecase.auth.AuthUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthHandler {
    private final AuthUseCase authUseCase;
    private final RequestValidator requestValidator;


    public Mono<ServerResponse> login(ServerRequest serverRequest) {
        log.info(LogMessage.LOGIN_SUCCESS.getMessage());
        return serverRequest.bodyToMono(LoginDto.class)
                .flatMap(requestValidator::validator)
                .flatMap(loginDto -> authUseCase.login(loginDto.email(), loginDto.password()))
                .map(data -> ResponseMapper.mapBodyResponse(ResponseCode.TOKEN_GENERATED, data))
                .flatMap(token -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(token)
                );
    }

    public Mono<ServerResponse> isEmailEqualToToken(ServerRequest serverRequest) {
        log.info(LogMessage.IS_VALID_EMAIL.getMessage());
        return serverRequest.bodyToMono(EmailEqualToTokenDto.class)
                .flatMap(requestValidator::validator)
                .flatMap(data -> authUseCase.isEmailEqualToToken(data.email()))
                .map(response -> ResponseMapper.mapBodyResponse(
                        response ? ResponseCode.CORRECT_EMAIL : BusinessErrorCode.INVALID_EMAIL
                        , response))
                .flatMap(responseDto -> ServerResponse.ok().bodyValue(responseDto));


    }

    public Mono<ServerResponse> findRoleByAuthHeaderToken(ServerRequest serverRequest) {
        log.info(LogMessage.FIND_ROLE_BY_NAME.getMessage());
        return authUseCase.getRoleByAuthHeader()
                .map(rol -> ResponseMapper.mapBodyResponse(ResponseCode.ROLE_FOUND, rol))
                .flatMap(response ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(response));

    }

}