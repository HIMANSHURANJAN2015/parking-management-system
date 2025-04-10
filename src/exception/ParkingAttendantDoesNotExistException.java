package exception;

public class ParkingAttendantDoesNotExistException extends RuntimeException {

    public ParkingAttendantDoesNotExistException(){

    }

    public ParkingAttendantDoesNotExistException(String message) {

        super(message);
    }
}
