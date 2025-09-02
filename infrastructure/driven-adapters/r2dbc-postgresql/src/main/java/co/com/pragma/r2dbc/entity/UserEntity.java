package co.com.pragma.r2dbc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @Column("user_id")
    private String id;
    private String name;
    private String password;
    private String lastname;
    private LocalDate birthdate;
    private String address;
    private String email;
    private String identity_document;
    private String phone_number;
    private String role_id;
    private Integer balance;

}