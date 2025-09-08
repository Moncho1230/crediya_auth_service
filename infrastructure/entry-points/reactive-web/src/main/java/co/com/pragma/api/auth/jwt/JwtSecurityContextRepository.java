package co.com.pragma.api.auth.jwt;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtSecurityContextRepository  implements ServerSecurityContextRepository {
    private final JwtAuthManage jwtAuthManage;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context){
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(h -> h.startsWith("Bearer "))
                .map(h -> h.substring(7))
                .map(token -> (Authentication) new UsernamePasswordAuthenticationToken(null, token))
                .flatMap(jwtAuthManage::authenticate)
                .map(SecurityContextImpl::new);
    }


}
