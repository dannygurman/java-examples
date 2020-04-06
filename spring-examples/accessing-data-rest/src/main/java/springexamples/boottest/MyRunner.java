package springexamples.boottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

//CommandLineRunner is an interface used to indicate that a bean should run when it is contained within a SpringApplication.
// A Spring Boot application can have multiple beans implementing CommandLineRunner.
// These can be ordered with @Order.

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Person p1 = new Person();
        p1.setFirstName("danny");
        p1.setLastName("gur");
        personRepository.save(p1);
    }

    }
