package red1xx8.reservationservice.reservation.repository;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import red1xx8.reservationservice.reservation.model.Event;
import red1xx8.reservationservice.reservation.model.ReservationStatus;

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

    @Column(name = "user_id")
    private Long userId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
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
