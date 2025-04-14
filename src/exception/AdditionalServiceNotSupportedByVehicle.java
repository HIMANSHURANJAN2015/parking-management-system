package exception;

public class AdditionalServiceNotSupportedByVehicle extends RuntimeException {
    public AdditionalServiceNotSupportedByVehicle() {

    }

    public AdditionalServiceNotSupportedByVehicle(String message) {
      super(message);
    }
}
