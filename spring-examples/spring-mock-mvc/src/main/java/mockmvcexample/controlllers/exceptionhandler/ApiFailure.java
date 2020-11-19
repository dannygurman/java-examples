package mockmvcexample.controlllers.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiFailure {

    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String CLIENT_ERROR = "Client Error";
    public static final String VALIDATION_ERROR = "Validation Error";
    public static final String NOT_FOUND = "Not Found";

    /**
     * Problem declaration.
     */
    private String error;
    /**
     * Problem detailed description.
     */
    private String description;

    public static ApiFailure internalError(final String message) {
        return ApiFailure.builder()
            .error(INTERNAL_SERVER_ERROR)
            .description(message)
            .build();
    }

    public static ApiFailure clientError(final String message) {
        return ApiFailure.builder()
            .error(CLIENT_ERROR)
            .description(message)
            .build();
    }

    public static ApiFailure notFound(final String message) {
        return ApiFailure.builder()
            .error(NOT_FOUND)
            .description(message)
            .build();
    }

}
