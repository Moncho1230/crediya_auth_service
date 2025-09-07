package co.com.pragma.r2dbc.transacional;

import co.com.pragma.model.shared.gateways.TransacionalGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

/**
 * Implementación de TransacionalGateway usando transacciones reactivas de Spring R2DBC.
 */
@Component
public class TransacionalGatewayImpl implements TransacionalGateway {
    private final TransactionalOperator transactionalOperator;

    public TransacionalGatewayImpl(ReactiveTransactionManager transactionManager) {
        this.transactionalOperator = TransactionalOperator.create(transactionManager);
    }

    @Override
    public <T> Mono<T> executeInTransaction(Mono<T> action) {
        return transactionalOperator.transactional(action);
    }
}
