package autopark.servise.exception;

public class ServiceException extends Exception {

        public ServiceException() {
        }

        public ServiceException(String message) {
            super(message);
        }

        public ServiceException(String message, Throwable cause) {
            super(message, cause);
        }

        public ServiceException(Throwable cause) {
            super(cause);
        }
    }
//НЕ ЗАБЫТЬ ПОТОМ БРОСИТЬ НА Service


