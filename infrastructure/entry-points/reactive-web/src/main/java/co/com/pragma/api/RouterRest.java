package co.com.pragma.api;

import co.com.pragma.api.auth.AuthHandler;
import co.com.pragma.api.auth.config.AuthPath;
import co.com.pragma.api.user.UserHandler;
import co.com.pragma.api.user.config.UserPath;
import co.com.pragma.model.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
@RequiredArgsConstructor
public class RouterRest {
    private final UserPath userPath;
    private final AuthPath authPath;

    @Bean

    public RouterFunction<ServerResponse> routerFunction(UserHandler userHandler, AuthHandler authHandler) {
        return route(POST(userPath.getUsers()), userHandler::save)
                .andRoute(POST(userPath.getExistsByEmailAndDocument()), userHandler::existsByDocumentAndEmail)
                .and(route(GET(userPath.getUsers()), userHandler::getAll))
                .andRoute(POST(authPath.getLogin()), authHandler::login)
                .andRoute(POST(authPath.getIsEmailEqualToToken()), authHandler::isEmailEqualToToken)
                .and(route(GET(authPath.getRoleByAuthHeaderToken()), authHandler::findRoleByAuthHeaderToken))
                ;
    }
}
