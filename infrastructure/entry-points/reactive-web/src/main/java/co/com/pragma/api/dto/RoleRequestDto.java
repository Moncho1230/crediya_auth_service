package co.com.pragma.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleRequesDto {
    @NotBlank(message = "{role.name.required}")
    private String name;

    @NotBlank(message = "{role.description.required}")
    private String description;
}
