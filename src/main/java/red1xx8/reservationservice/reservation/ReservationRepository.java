package red1xx8.reservationservice.reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ReservationRepository extends JpaRepository<ReservationEntity , Long>, JpaSpecificationExecutor<ReservationEntity> {


    Page<ReservationEntity> findAll(
            Specification<ReservationEntity> spec,
            Pageable pageable
    );


}
