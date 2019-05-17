package spring.examples.config;

/**
 * Created by DannyG on 09/01/2017.
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.examples.HelloWorld;
import spring.examples.hello.impl.HelloWorldImpl;

@Configuration
public class AppConfig {

    @Bean(name="helloBean")
    public HelloWorld helloWorld() {
        return new HelloWorldImpl();
    }


}
