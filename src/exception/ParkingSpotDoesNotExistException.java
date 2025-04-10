package exception;

public class ParkingSpotDoesNotExistException extends RuntimeException {
    public ParkingSpotDoesNotExistException() {
    }

    public ParkingSpotDoesNotExistException(String message) {
        super(message);
    }
}