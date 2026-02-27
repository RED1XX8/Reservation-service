package red1xx8.reservationservice.reservation;

public enum ReservationStatus {
    APPROVED{
        @Override
        public Boolean canTransactional(ReservationStatus target){
            return target == CANCELLED || target == COMPLETED;
        }
    },
    PENDING{
        @Override
        public Boolean canTransactional(ReservationStatus target){
            return target == APPROVED || target == CANCELLED;
        }
    },
    CANCELLED{
        @Override
        public Boolean canTransactional(ReservationStatus target){
            return false;
        }
    },
    COMPLETED{
        @Override
        public Boolean canTransactional(ReservationStatus target){
            return false;
        }
    };

    public Boolean canBeUpdated(){
        return this != COMPLETED && this != CANCELLED;
    }

    public abstract Boolean canTransactional(ReservationStatus target);
}
