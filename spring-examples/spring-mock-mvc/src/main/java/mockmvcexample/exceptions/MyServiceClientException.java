package mockmvcexample.exceptions;

/**
 * Client exception class for exceptions inside of service .
 */
public class MyServiceClientException  extends MyServiceException{

    public MyServiceClientException(String message) {
        super(message);
    }

    public MyServiceClientException(String message, Throwable cause) {
        super(message, cause);
    }
}

