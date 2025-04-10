package exception;

public class ParkingFloorDoesNotExistException extends RuntimeException {
    public ParkingFloorDoesNotExistException() {
    }

    public ParkingFloorDoesNotExistException(String message) {
        super(message);
    }
}
