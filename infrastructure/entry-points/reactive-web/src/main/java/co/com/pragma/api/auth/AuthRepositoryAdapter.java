package co.com.pragma.api.auth;

import co.com.pragma.api.auth.config.JWTConfig;
import co.com.pragma.model.auth.AuthUser;
import co.com.pragma.model.auth.gateways.AuthRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Repository
public class AuthRepositoryAdapter implements AuthRepository {

    private final SecretKey key;
    private final Duration tokenValidity;

    public AuthRepositoryAdapter(JWTConfig jwtConfig) {
        this.key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
        this.tokenValidity = Duration.ofMinutes(jwtConfig.getExpiredMinutes());
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public Mono<String> generateToken(AuthUser authorizedUser) {
        return Mono.just(Jwts.builder()
                .claims(Map.of(TokenClaims.EMAIL.getValue(), authorizedUser.email(), TokenClaims.ROLE.getValue(), authorizedUser.role()))
                .subject(authorizedUser.document())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(tokenValidity)))
                .signWith(key)
                .compact());
    }

    @Override
    public Mono<AuthUser> validateToken(String token) {
        final Claims claims = extractAllClaims(token);
        return Mono.just(claims)
                .flatMap(claimsVerified -> Mono.just(
                        new AuthUser(
                                claimsVerified.getSubject(),
                                (String) claimsVerified.get(TokenClaims.EMAIL.getValue()),
                                (String) claimsVerified.get(TokenClaims.ROLE.getValue()))
                ));
    }

    @Override
    public Mono<String> getTokenEmail() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(authentication -> {
                    AuthUser authorizedUser = (AuthUser) authentication.getPrincipal();
                    return authorizedUser.email();
                });
    }

}
