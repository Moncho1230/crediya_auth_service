package co.com.pragma.api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserResponseDto {
  
    private String name;
    private String lastname;
    private LocalDate birthdate;
    private String address;
    private String email;
    private String role_id;
    private Integer balance;

}
