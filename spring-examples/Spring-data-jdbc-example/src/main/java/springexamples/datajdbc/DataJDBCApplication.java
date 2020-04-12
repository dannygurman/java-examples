package springexamples.datajdbc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Configuration
@PropertySource("classpath:application.properties")
public class DataJDBCApplication  implements CommandLineRunner {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentJdbcRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(DataJDBCApplication.class, args);
    }

    @Override
    public void run(String...args)  throws Exception {
        logger.warn("START");
        Student student1 = repository.findById(1L);
        logger.info("Student id 1 -> {}", student1);
        logger.info("All users 1 -> {}", repository.findAll());
        logger.info("Inserting -> {}", repository.insert(new Student(3L, "z", "4")));
        logger.info("Update 10001 -> {}", repository.update(new Student(1L, "Name-Updated", "New-Passport")));
        repository.deleteById(2L);
        logger.info("All users 2 -> {}", repository.findAll());

    }

}
