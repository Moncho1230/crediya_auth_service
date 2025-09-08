package co.com.pragma.api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record EmailEqualToTokenDto(
        @NotNull
        @Email
        String email
) {
}
