package co.com.pragma.api.dto;

public record ResponseDto<T>(
        String message,
        String code,
        T data
) { }
