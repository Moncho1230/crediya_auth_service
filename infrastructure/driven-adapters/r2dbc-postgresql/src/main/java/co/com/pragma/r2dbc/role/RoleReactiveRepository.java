package co.com.pragma.r2dbc.role;

import co.com.pragma.model.role.Role;
import co.com.pragma.r2dbc.role.entity.RoleEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RoleReactiveRepository extends ReactiveCrudRepository<RoleEntity, UUID>,
        ReactiveQueryByExampleExecutor<RoleEntity> {
    Mono<Role> findByName(String name);

}
