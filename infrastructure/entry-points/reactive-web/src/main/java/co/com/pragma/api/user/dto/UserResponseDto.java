package co.com.pragma.api.user.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserResponseDto {
  
    private String name;
    private String lastName;
    private String document;
    private LocalDate birthdate;
    private String address;
    private String email;
    private UUID roleId;
    private Integer balance;
    private String phoneNumber;

}
