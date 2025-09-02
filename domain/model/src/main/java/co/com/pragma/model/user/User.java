package co.com.pragma.model.user;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private String user_id;
    private String name;
    private String lastname;
    private String password;
    private LocalDate birthdate;
    private String address;
    private String email;
    private String identity_document;
    private String phone_number;
    private String role_id;
    private Integer balance;


}
