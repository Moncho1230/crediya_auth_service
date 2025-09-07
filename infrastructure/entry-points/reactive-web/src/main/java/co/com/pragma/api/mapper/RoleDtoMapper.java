package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.RoleRequestDto;
import co.com.pragma.api.dto.RoleResponseDto;
import co.com.pragma.model.role.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleDtoMapper {

    Role toRole(RoleRequestDto createRoleDto);
    RoleResponseDto toResponse(Role role);
}
