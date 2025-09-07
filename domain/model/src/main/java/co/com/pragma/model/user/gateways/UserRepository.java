package co.com.pragma.model.user.gateways;

import co.com.pragma.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> save(User user);
    Mono<User> findByEmail(String email);
    Mono<Boolean> existsByEmailOrDocument(String email, String document);
    Mono<Boolean> existsByEmailAndDocument(String email, String document);
    Mono<String> findRoleNameByEmail(String email);
    Flux<User> findAll();
}
