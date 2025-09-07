package co.com.pragma.r2dbc.user;

import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.r2dbc.user.entity.UserEntity;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserReactiveRepositoryAdapter extends ReactiveAdapterOperations<
    User, UserEntity, String, UserReactiveRepository
> implements UserRepository {
    public UserReactiveRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, User.class));
    }


    @Override
    public Mono<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Mono<Boolean> existsByEmailOrDocument(String email, String document) {
        return repository.existsByEmailOrDocument(email, document);
    }

    @Override
    public Mono<Boolean> existsByEmailAndDocument(String email, String document) {
        return repository.existsByEmailAndDocument(email, document);
    }

    @Override
    public Mono<String> findRoleNameByEmail(String email) {
        return repository.findRoleNameByEmail(email);
    }
}
