package by.itacademy.appliances.service.exception;

public class ServisException extends Exception {
    public ServisException() {
    }

    public ServisException(String message) {
        super(message);
    }

    public ServisException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServisException(Throwable cause) {
        super(cause);
    }
}
