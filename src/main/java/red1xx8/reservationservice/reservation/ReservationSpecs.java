package red1xx8.reservationservice.reservation;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class ReservationSpecs {

    public static Specification<ReservationEntity> hasReservationId(Long id){
        return (root , query , criteriaBuilder) ->
                id == null? criteriaBuilder.conjunction(): criteriaBuilder.equal(root.get("id") , id);
    }

    public static Specification<ReservationEntity> hasUserId(Long userId){
        return (root, query, criteriaBuilder) ->
                userId == null? criteriaBuilder.conjunction(): criteriaBuilder.equal(root.get("user").get("id") , userId);
    }

    public static Specification<ReservationEntity> hasTableId(Long tableId){
        return (root, query, criteriaBuilder) ->
                tableId == null? criteriaBuilder.conjunction(): criteriaBuilder.equal(root.get("table").get("id") , tableId);
    }

    public static Specification<ReservationEntity> inRangeTime(LocalDateTime start , LocalDateTime end){
        return (root , query , criteriaBuilder) ->{
            if(start == null || end == null) return null;
            return criteriaBuilder.and(
                    criteriaBuilder.lessThanOrEqualTo(root.get("startReservation") , end),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("endReservation") , start)
            );
        };
    }

    public static Specification<ReservationEntity> hasEvent(Event event){
        return (root, query, criteriaBuilder) ->
                event == null? criteriaBuilder.conjunction(): criteriaBuilder.equal(root.get("event") , event);
    }

    public static Specification<ReservationEntity> hasStatus(ReservationStatus status){
        return (root, query, criteriaBuilder) ->
                status == null? criteriaBuilder.conjunction(): criteriaBuilder.equal(root.get("status"), status);
    }

}
