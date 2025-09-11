package co.com.pragma.api.auth.config;


import co.com.pragma.api.auth.jwt.JwtAuthManage;
import co.com.pragma.api.auth.jwt.JwtSecurityContextRepository;
import co.com.pragma.api.user.config.UserPath;
import co.com.pragma.model.shared.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthManage jwtAuthManage;
    private final JwtSecurityContextRepository securityContextRepository;
    private final UserPath userPath;
    private final AuthPath authPath;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authenticationManager(jwtAuthManage)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange(exchange ->
                        exchange
                                .pathMatchers(HttpMethod.POST, userPath.getUsers()).hasAnyRole(Roles.ADVISER.getValue(), Roles.ADMIN.getValue())
                                .pathMatchers(userPath.getExistsByEmailAndDocument()).hasAnyRole(Roles.CLIENT.getValue(), Roles.ADVISER.getValue())
                                .pathMatchers(authPath.getRoleByAuthHeaderToken()).hasRole(Roles.ADVISER.getValue())
                                .pathMatchers(authPath.getIsEmailEqualToToken()).hasAnyRole(Roles.CLIENT.getValue(), Roles.ADMIN.getValue(), Roles.ADVISER.getValue())
                                .pathMatchers(authPath.getLogin()).permitAll()
                                .anyExchange().permitAll()
                )
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .build();
    }
}
