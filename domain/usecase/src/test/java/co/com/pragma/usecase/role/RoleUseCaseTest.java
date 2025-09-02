package co.com.pragma.usecase.role;

import co.com.pragma.model.role.Role;
import co.com.pragma.model.role.gateways.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RoleUseCaseTest {
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private RoleUseCase roleUseCase;
    private Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleUseCase = new RoleUseCase(roleRepository);
        role = new Role();
        role.setRole_id("role1");
        role.setName("ADMIN");
        role.setDescription("Administrator role");
    }

    @Test
    void createRole_success() {
        when(roleRepository.CreateRol(any(Role.class))).thenReturn(Mono.just(role));
        StepVerifier.create(roleUseCase.CreateRole(role))
                .expectNext(role)
                .verifyComplete();
    }
}
