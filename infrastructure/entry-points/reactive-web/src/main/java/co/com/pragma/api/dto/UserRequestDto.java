package co.com.pragma.api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UserRequestDto {


    @NotBlank(message = "{user.identity_document.required}")
    @Pattern(regexp = "\\d{10}", message = "{user.identity_document.format}")
    private String id;

    @NotBlank(message = "{user.name.required}")
    private String name;

    @NotBlank(message = "{user.lastname.required}")
    private String lastName;

    @NotBlank(message = "{user.password.required}")
    @Size(min = 8, max = 64, message = "{user.password.size}")
    private String password;

    @NotNull(message = "{user.birthdate.required}")
    @Past(message = "{user.birthdate.past}")
    private LocalDate birthdate;

    @NotBlank(message = "{user.address.required}")
    private String address;

    @NotNull(message = "{user.email.required}")
    @Email(message = "{user.email.format}")
    private String email;

    @NotBlank(message = "{user.phone.required}")
    @Pattern(regexp = "\\d{7,10}", message = "{user.phone.format}")
    private String phoneNumber;

    @NotNull(message = "{user.roleId.required}")
    private Integer roleId;

    @NotNull(message = "{user.balance.required}")
    @Min(value = 0, message = "{user.balance.min}")
    @Max(value = 1500000, message = "{user.balance.max}")
    private Integer balance;


}
