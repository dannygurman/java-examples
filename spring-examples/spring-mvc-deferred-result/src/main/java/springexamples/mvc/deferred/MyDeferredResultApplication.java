package springexamples.mvc.deferred;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//NOTE - exclude spring security otherwise enabled by default !!!

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MyDeferredResultApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyDeferredResultApplication.class, args);
    }
}
