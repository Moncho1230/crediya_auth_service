package co.com.pragma.usecase.role;

import co.com.pragma.model.role.Role;
import co.com.pragma.model.role.gateways.RoleRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * Caso de uso para la lógica de negocio relacionada con roles.
 * Proporciona métodos para la creación de roles de forma reactiva.
 */
@RequiredArgsConstructor
public class RoleUseCase {

    private final RoleRepository roleRepository;

    /**
     * Crea un nuevo rol.
     *
     * @param role el rol a crear
     * @return un Mono que emite el rol creado
     */
    Mono<Role> CreateRole(Role role){
        return roleRepository.CreateRol(role);
    }
}
