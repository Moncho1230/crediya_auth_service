package co.com.pragma.api.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Schema(name = "ApiError", description = "Estructura estándar de error")
public class ApiError {
    @Schema(example = "VALIDATION_ERROR")
    private String code;

    @Schema(example = "DTO validation failed: email: must be a well-formed email address")
    private String message;

    @Schema(example = "2025-08-28T13:45:02Z")
    private String timestamp;

    @Schema(description = "Detalles adicionales del error")
    private Object details;
}
