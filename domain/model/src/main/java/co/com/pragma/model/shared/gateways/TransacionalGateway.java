package co.com.pragma.model.shared.gateways;

import reactor.core.publisher.Mono;

/**
 * Interfaz gateway para ejecutar acciones dentro de una transacción en un contexto reactivo.
 */
@FunctionalInterface
public interface TransacionalGateway {
  /**
   * Ejecuta la acción dada dentro de una transacción.
   *
   * @param action la acción Mono a ejecutar de forma transaccional
   * @param <T> el tipo emitido por el Mono
   * @return un Mono que emite el resultado de la acción
   */
    <T> Mono<T> executeInTransaction(Mono<T> action);
}
