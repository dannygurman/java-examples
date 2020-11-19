package mockmvcexample.exceptions;
/**
 * Server exception class for exceptions inside of service .
 */
public class MyServiceServerException extends MyServiceException {

    public MyServiceServerException(final String message) {
        super(message);
    }

    public MyServiceServerException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

