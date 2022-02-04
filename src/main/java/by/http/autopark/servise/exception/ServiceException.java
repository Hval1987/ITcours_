package by.http.autopark.servise.exception;

/**
 * in this enumeration we describe all possible commands for the controller
 */

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



