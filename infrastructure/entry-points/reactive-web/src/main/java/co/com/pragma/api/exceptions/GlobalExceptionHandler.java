package co.com.pragma.api.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Component
@Order(-2)
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    private final MessageSource messages;

    public GlobalExceptionHandler(MessageSource messages) {
        this.messages = messages;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ErrorCode code = ErrorCode.INTERNAL_ERROR;
        Object[] args = new Object[0];
        String fallback = ex.getMessage() == null ? "Unexpected error" : ex.getMessage();

        if (ex instanceof WebException we) {
            code = we.code;
            args = we.args;
        } else if (ex instanceof ConstraintViolationException) {
            code = ErrorCode.VALIDATION_ERROR;
        }

        var locale = Locale.getDefault();
        String message = messages.getMessage(code.messageKey, args, code.messageKey, locale);

        var response = exchange.getResponse();
        response.setStatusCode(code.status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String body = "{\"code\":\"" + code.name() + "\",\"message\":\"" +
                escapeJson(message != null ? message : fallback) + "\"}";
        var buf = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buf));
    }

    private static String escapeJson(String s) {
        return s.replace("\"", "\\\"");
    }
}