package spring.examples.annotations;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MyBean {

    public MyBean() {
        System.out.println("MyBean instance created");
    }

    @PostConstruct
    private void init() {
        System.out.println("MyBean Verifying Resources");
    }

    @PreDestroy
    private void shutdown() {
        System.out.println("MyBean Shutdown All Resources");
    }

    //f there is a method named shutdown or close then spring container will try to automatically configure
    // them as callback methods when bean is being destroyed.
    public void close() {
        System.out.println("MyBean Closing All Resources");
    }
}