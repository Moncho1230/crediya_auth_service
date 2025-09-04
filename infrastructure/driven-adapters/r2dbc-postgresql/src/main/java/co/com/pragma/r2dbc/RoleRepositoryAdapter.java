package co.com.pragma.r2dbc;

import co.com.pragma.model.role.Role;
import co.com.pragma.model.role.gateways.RoleRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Implementación base de RoleRepository para infraestructura.
 */
@Repository
public class RoleRepositoryAdapter implements RoleRepository {
    @Override
    public Mono<Role> CreateRol(Role role) {
        //TODO: Implementación base, reemplaza con lógica real de persistencia
        return Mono.just(role);
    }
}
