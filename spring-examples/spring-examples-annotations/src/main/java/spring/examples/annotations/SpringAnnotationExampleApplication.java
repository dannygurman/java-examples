package spring.examples.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringAnnotationExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAnnotationExampleApplication.class, args);
    }
}
