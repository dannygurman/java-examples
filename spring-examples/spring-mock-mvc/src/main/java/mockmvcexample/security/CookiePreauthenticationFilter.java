package mockmvcexample.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.function.Supplier;

import static mockmvcexample.security.SecurityConstants.COOKIE_NAME;

@Slf4j
public class CookiePreauthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {

        Supplier<PreAuthenticatedCredentialsNotFoundException> exceptionSupplier =
            () -> new PreAuthenticatedCredentialsNotFoundException(COOKIE_NAME + " header not found in request.");
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                throw new PreAuthenticatedCredentialsNotFoundException("no cookies were sent ; could not get " + COOKIE_NAME);
            }

            Object cookieValue =  Arrays.stream(cookies).filter(cookie -> cookie.getName().
                equals(COOKIE_NAME)).map(Cookie::getValue).findFirst()
                .orElseThrow(exceptionSupplier);
            return cookieValue;
        } catch (PreAuthenticatedCredentialsNotFoundException ex) {
            logger.error("getPreAuthenticatedPrincipal failed", ex);
            return "NoSessionError";
        }

    }

    /**
     * Credentials aren't usually applicable, but if a {@code credentialsRequestHeader} is set, this will be read and used
     * as the credentials value. Otherwise a dummy value will be used.
     */
    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }
}