package co.com.pragma.api.user.mapper;

import co.com.pragma.api.user.dto.CreateUserRequestDto;
import co.com.pragma.api.user.dto.UserResponseDto;
import co.com.pragma.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mapping(source ="birthdate", target = "birthdate",dateFormat = "yyyy-MM-dd")
    @Mapping(target = "roleId",ignore = true)
    @Mapping(target = "id", ignore = true)
    User toUser(CreateUserRequestDto createUserDto);


    UserResponseDto toUserResponseDto(User user);

}