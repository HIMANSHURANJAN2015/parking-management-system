package exception;

public class UnsupportedAdditionalService extends RuntimeException {
    public UnsupportedAdditionalService() {

    }

    public UnsupportedAdditionalService(String message) {
        super(message);
    }
}