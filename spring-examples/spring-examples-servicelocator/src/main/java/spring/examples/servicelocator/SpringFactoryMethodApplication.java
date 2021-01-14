package spring.examples.servicelocator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:9000/api/workers/json
 * http://localhost:9000/api/workers/csv
 * http://localhost:9000/api/workers/xml
 */
@SpringBootApplication
public class SpringFactoryMethodApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringFactoryMethodApplication.class, args);
  }
}
