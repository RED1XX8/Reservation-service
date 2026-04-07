package red1xx8.reservationsystem.auth.repository;


import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


    public Optional<UserEntity> findByUserNameOrNumberPhone(String userName , String numberPhone);

    public boolean existsByUserName(@NotNull String s);

    public boolean existsByNumberPhone(@NotNull String s);



    public Page<UserEntity> findAll(
            Specification<UserEntity> spec,
            Pageable pageable
    );
}
