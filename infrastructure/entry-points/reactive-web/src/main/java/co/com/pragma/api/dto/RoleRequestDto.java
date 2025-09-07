package co.com.pragma.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleRequestDto {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
