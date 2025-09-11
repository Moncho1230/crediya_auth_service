package co.com.pragma.r2dbc.user.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table("users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Column("id")
    private UUID id;
    @Column("name")
    private String name;
    @Column("password")
    private String password;
    @Column("lastname")
    private String lastName;
    @Column("document")
    private  String document;
    @Column("birthdate")
    private LocalDate birthdate;
    @Column("address")
    private String address;
    @Column("email")
    private String email;
    @Column("phone_number")
    private String phoneNumber;
    @Column("role_id")
    private UUID roleId;
    @Column("balance")
    private Integer balance;

}