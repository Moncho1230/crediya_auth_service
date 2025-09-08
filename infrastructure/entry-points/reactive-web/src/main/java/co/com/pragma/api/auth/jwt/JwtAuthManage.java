package co.com.pragma.api.auth.jwt;

import co.com.pragma.usecase.auth.AuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthManage implements ReactiveAuthenticationManager {

    private final AuthUseCase authUseCase;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        return authUseCase.ValidToken(token)
                .map(authUser ->
                        new UsernamePasswordAuthenticationToken(authUser,
                                token,
                                List.of(new SimpleGrantedAuthority("ROLE_"+ authUser.role())))
                );
    }
}
