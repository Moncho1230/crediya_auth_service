package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.UserRequestDto;
import co.com.pragma.api.dto.UserResponseDto;
import co.com.pragma.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para transformar objetos de transporte (DTO) en entidades de dominio.
 *
 * <p>
 * Implementado automáticamente por <b>MapStruct</b>, evitando
 * boilerplate de setters/getters.
 * </p>
 *
 * <p>
 * Detalles:
 * <ul>
 *   <li>Anotado con {@code @Mapper(componentModel = "spring")} para que Spring
 *       lo registre como un bean inyectable.</li>
 *   <li>Convierte un {@link UserRequestDto} recibido en la API a {@link User}
 *       del dominio.</li>
 *   <li>La anotación {@link Mapping} se usa para indicar cómo transformar campos
 *       específicos si hay diferencias de nombre o de tipo.</li>
 * </ul>
 * </p>
 *
 * <p><b>Nota:</b> Si ambos lados (DTO y dominio) usan {@link java.time.LocalDate}
 * no es necesario indicar {@code dateFormat}. Ese parámetro se usa
 * cuando el origen es {@link String} y el destino un {@link -LocalDate}.</p>
 */
@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    /**
     * Convierte un DTO de creación/entrada en un objeto de dominio Usuario.
     *
     * @param createUserDto DTO con los datos del usuario.
     * @return instancia de {@link User} lista para ser procesada por el caso de uso.
     */
    @Mapping(source = "birthdate", target = "birthdate", dateFormat = "yyyy-MM-dd")
    User toUser(UserRequestDto createUserDto);
    UserResponseDto toResponse(User user);

}
