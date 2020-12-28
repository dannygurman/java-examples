package spring.examples.profiles.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("spring.examples.profiles")
@PropertySource(value = "classpath:application.properties")
public class MySpringProfilesConfig {

}
