package spring.examples;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by DannyG on 09/01/2017.
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        HelloWorld obj = (HelloWorld) context.getBean("helloBean");
        obj.printHello();
    }

}
