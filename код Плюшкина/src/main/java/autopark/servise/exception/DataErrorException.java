package autopark.servise.exception;

public class DataErrorException extends Exception {
    public DataErrorException() {
    }

    public DataErrorException(String message) {
        super(message);
    }

    public DataErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataErrorException(Throwable cause) {
        super(cause);
    }
}
