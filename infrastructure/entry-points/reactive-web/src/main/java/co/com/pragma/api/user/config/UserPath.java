package co.com.pragma.api.user.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Clase de configuración que centraliza los paths de usuario
 * para la capa de entry-points (Router).
 *
 * <p>
 * Se usa junto con {@link org.springframework.boot.context.properties.EnableConfigurationProperties}
 * para inyectar valores definidos en el <code>application.yml</code> o <code>application.properties</code>,
 * evitando hardcodear rutas en el código.
 * </p>
 *
 * <p><b>Ejemplo en application.yml:</b></p>
 * <pre>
 * routes:
 *   paths:
 *     users: /api/v1/users
 *     userByEmail: /api/v1/users/{email}
 * </pre>
 *
 * <p>Con esto, las rutas quedan centralizadas y fáciles de versionar o cambiar.</p>
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "routes.paths.users")
public class UserPath {

    private String users;
    private String findByEmail;
    private String existsByEmailAndDocument;
}
