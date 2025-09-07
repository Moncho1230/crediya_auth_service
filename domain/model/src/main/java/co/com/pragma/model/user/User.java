package co.com.pragma.model.user;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Objects;
import java.util.UUID;
import java.time.LocalDate;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private UUID id;
    private String name;
    private String lastName;
    private String document;
    private String password;
    private LocalDate birthdate;
    private String address;
    private String email;
    private String phoneNumber;
    private UUID roleId;
    private Integer balance;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof  User user)) return false;
        return Objects.equals(document, user.document)&& Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(document, email);
    }


}
