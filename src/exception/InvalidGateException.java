package exception;

public class InvalidGateException extends RuntimeException {
    public InvalidGateException() {

    }

    public InvalidGateException(String message) {

        super(message);
    }
}
