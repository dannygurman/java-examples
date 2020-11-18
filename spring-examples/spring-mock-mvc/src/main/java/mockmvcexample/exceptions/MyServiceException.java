package mockmvcexample.exceptions;

/**
 * Base exception class for exceptions inside service.
 */
public class MyServiceException extends Exception {

    public MyServiceException(final String message) {
        super(message);
    }

    public MyServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

