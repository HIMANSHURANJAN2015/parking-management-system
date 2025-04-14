package exception;

public class ParkingTicketNotFoundException extends RuntimeException {

    public ParkingTicketNotFoundException() {

    }

    public ParkingTicketNotFoundException(String message) {
        super(message);
    }
}
