package app.exceptions;

public class DisabledFilterException extends RuntimeException {
    public DisabledFilterException(String message) {
        super(message);
    }
}
