package red1xx8.reservationservice.auth.repository;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import red1xx8.reservationservice.auth.model.Roles;
import red1xx8.reservationservice.auth.model.UserStatus;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name" , unique = true)
    private String userName ;

    @Column(name = "number_phone" , unique = true)
    private String numberPhone;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Roles role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

}
