package springexamples.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Each application built using Spring Boot needs merely to define the main entry point.
// This is usually a Java class with the main method, annotated with @SpringBootApplication:

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
