package exception;

public class ParkingGateDoesNotExistException extends RuntimeException {

    public ParkingGateDoesNotExistException() {

    }

    public ParkingGateDoesNotExistException(String message) {

        super(message);
    }
}
