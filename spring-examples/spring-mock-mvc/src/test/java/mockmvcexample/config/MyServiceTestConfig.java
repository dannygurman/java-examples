package mockmvcexample.config;

import mockmvcexample.security.MyUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class MyServiceTestConfig {

    @Bean("testUserDetailsService")
    public static UserDetailsService userDetailsService() {
        return s -> getUserDetails();

    }
    private static UserDetails getUserDetails() {
        MyUserDetails userDetails = new MyUserDetails();
        userDetails.setIsAdmin(true);
        return  userDetails;
    }

}





