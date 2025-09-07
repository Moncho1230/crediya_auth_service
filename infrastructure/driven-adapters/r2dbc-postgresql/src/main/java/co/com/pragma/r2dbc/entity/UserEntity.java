package co.com.pragma.r2dbc.entity;

import lombok.*;
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
    @Column("id")
    private String id;
    @Column("name")
    private String name;
    @Column("password")
    private String password;
    @Column("lastname")
    private String lastName;
    @Column("birthdate")
    private LocalDate birthdate;
    @Column("address")
    private String address;
    @Column("email")
    private String email;
    @Column("phonenumber")
    private String phoneNumber;
    @Column("roleid")
    private Integer roleId;
    @Column("balance")
    private Integer balance;

}