package red1xx8.reservationservice.reservation;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import red1xx8.reservationservice.table.TableEntity;
import red1xx8.reservationservice.user.UserEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id" , nullable = false)
    private TableEntity table;

    @Column(name = "start_reservation" , nullable = false)
    private LocalDateTime startReservation;

    @Column(name = "end_reservation" , nullable = false)
    private LocalDateTime endReservation;

    @Column(name = "comment" , nullable = false)
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "event" , nullable = false)
    private Event event;

    @Enumerated(EnumType.STRING)
    @Column(name = "status" , nullable = false)
    private ReservationStatus status;

}
