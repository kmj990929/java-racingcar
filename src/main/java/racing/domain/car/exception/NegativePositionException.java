package racing.domain.car.exception;

public class NegativePositionException extends IllegalArgumentException {

    public NegativePositionException(String message) {
        super(message);
    }
}