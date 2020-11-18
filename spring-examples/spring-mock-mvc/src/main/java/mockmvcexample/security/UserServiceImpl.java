package mockmvcexample.security;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@Service
@Slf4j

public class UserServiceImpl implements UserDetailsService {


    @Getter
    private MyUserDetails currentUserDetails;

    @Override
    public MyUserDetails loadUserByUsername(final String userSessionKey) throws UsernameNotFoundException {

        if (StringUtils.isEmpty(userSessionKey) || userSessionKey.equals("NoSessionError")) {
            log.info("login request - cookie  is invalid or not provided, only public resources are permitted");
            throw new UsernameNotFoundException(userSessionKey);
        }

        try {
            log.debug("login request - start with cookie= {}", userSessionKey);

            this.currentUserDetails = getUserDetailsInternal( userSessionKey);
            return currentUserDetails;

        } catch (Exception ex) {
            log.error("login request failed", ex);
            throw ex;
        } finally {
            log.debug("login request - end");
        }
    }

    private MyUserDetails getUserDetailsInternal(String userSessionKey) {
        boolean isAdmin = false;
        if ("admin".equals(userSessionKey)){
            isAdmin = true;
        }
        return new MyUserDetails(isAdmin);
    }

}
