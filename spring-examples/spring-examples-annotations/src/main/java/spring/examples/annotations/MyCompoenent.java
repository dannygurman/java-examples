package spring.examples.annotations;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MyCompoenent {

    public MyCompoenent() {
        System.out.println("MyCompoenent instance created");
    }

    @PostConstruct
    private void init() {
        System.out.println("MyCompoenent Verifying Resources");
    }

    @PreDestroy
    private void shutdown() {
        System.out.println("MyCompoenent Shutdown All Resources");
    }

    public void close() {
        System.out.println("MyCompoenent Closing All Resources");
    }
}
