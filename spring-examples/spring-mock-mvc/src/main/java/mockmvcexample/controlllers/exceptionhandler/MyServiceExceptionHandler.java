package mockmvcexample.controlllers.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import mockmvcexample.exceptions.MyServiceClientException;
import mockmvcexample.exceptions.MyServiceException;
import mockmvcexample.exceptions.MyServiceServerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles exceptions that happen in the controllers - @ControllerAdvice .
 */
@ControllerAdvice
@Slf4j
public class MyServiceExceptionHandler {

    @ExceptionHandler(MyServiceException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiFailure handleDeviceManagerException(final HttpServletRequest request, final MyServiceException exception) {
        logError(HttpStatus.INTERNAL_SERVER_ERROR, request, exception);

        return ApiFailure.internalError(exception.getMessage());
    }

    @ExceptionHandler(MyServiceServerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiFailure handleDeviceManagerServerException(final HttpServletRequest request, final MyServiceServerException exception) {
        logError(HttpStatus.INTERNAL_SERVER_ERROR, request, exception);

        return ApiFailure.internalError(exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiFailure handleDeviceManagerException(final HttpServletRequest request, final IllegalArgumentException exception) {
        logError(HttpStatus.BAD_REQUEST, request, exception);

        return ApiFailure.internalError(exception.getMessage());
    }

    @ExceptionHandler(MyServiceClientException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiFailure handleDeviceManagerClientException(final HttpServletRequest request, final MyServiceClientException exception) {
        logError(HttpStatus.BAD_REQUEST, request, exception);

        return ApiFailure.clientError(exception.getMessage());
    }

    private static void logError(final HttpStatus httpStatus, final HttpServletRequest request, final Exception e) {
        log.error("Server Error happened:\n{}\n{}", "Return Code: "+httpStatus.getReasonPhrase(), request.getRequestURI(), e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiFailure handleDeviceManagerException(final HttpServletRequest request, final Exception exception) {
        logError(HttpStatus.INTERNAL_SERVER_ERROR, request, exception);

        return ApiFailure.internalError(exception.getMessage());
    }

}
