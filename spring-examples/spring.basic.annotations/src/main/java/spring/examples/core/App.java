package spring.examples.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.examples.HelloWorld;
import spring.examples.config.AppConfig;

/**
 * Created by DannyG on 09/01/2017.
 */
public class App {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld obj = (HelloWorld) context.getBean("helloBean");

        obj.printHelloWorld("Spring3 Java Config");

    }

}
