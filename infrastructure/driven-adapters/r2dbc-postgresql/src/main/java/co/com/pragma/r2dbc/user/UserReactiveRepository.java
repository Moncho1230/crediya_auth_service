package co.com.pragma.r2dbc.user;

import co.com.pragma.model.user.User;
import co.com.pragma.r2dbc.user.entity.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;


public interface UserReactiveRepository extends ReactiveCrudRepository<UserEntity, String>, ReactiveQueryByExampleExecutor<UserEntity> {

    Mono<User> findByEmail(String email);
    Mono<Boolean> existsByEmailAndDocument(String email, String document);
    Mono<Boolean> existsByEmailOrDocument(String email, String document);
    @Query("SELECT r.name FROM users u JOIN roles r ON u.role_id = r.rol_id WHERE u.email = :email")
    Mono<String> findRoleNameByEmail(String email);

}
