package co.com.pragma.usecase.user;

import co.com.pragma.model.shared.exceptions.AlreadyExistsException;
import co.com.pragma.model.shared.exceptions.ValidationException;
import co.com.pragma.model.shared.gateways.TransacionalGateway;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserUseCaseTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private TransacionalGateway transacionalGateway;
    @InjectMocks
    private UserUseCase userUseCase;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userUseCase = new UserUseCase(userRepository, transacionalGateway);
        user = User.builder()
                .user_id("1")
                .name("Test")
                .lastname("User")
                .password("password123")
                .birthdate(LocalDate.of(2000, 1, 1))
                .address("Address")
                .email("test@email.com")
                .identity_document("1234567890")
                .phone_number("1234567890")
                .role_id("role1")
                .balance(1000)
                .build();
    }

    @Test
    void createUser_success() {
        when(userRepository.UserExistsByEmail(user.getEmail())).thenReturn(Mono.just(false));
        when(transacionalGateway.executeInTransaction(any(Mono.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(userRepository.CreateUser(user)).thenReturn(Mono.just(user));

        StepVerifier.create(userUseCase.createUser(user))
                .expectNext(user)
                .verifyComplete();
    }

    @Test
    void createUser_alreadyExists() {
        when(userRepository.UserExistsByEmail(user.getEmail())).thenReturn(Mono.just(true));

        StepVerifier.create(userUseCase.createUser(user))
                .expectError(AlreadyExistsException.class)
                .verify();
    }



}
