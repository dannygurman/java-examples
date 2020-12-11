package springexamples.asynccontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Each application built using Spring Boot needs merely to define the main entry point.
// This is usually a Java class with the main method, annotated with @SpringBootApplication:

@SpringBootApplication
public class AsyncApiExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(AsyncApiExampleApplication.class, args);
    }
}
