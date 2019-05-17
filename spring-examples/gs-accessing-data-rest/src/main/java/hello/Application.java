package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//SpringBootApplication - Indicates a configuration class that declares one or more @Bean methods and also
// triggers auto-configuration and component scanning.
// This is a convenience annotation that is equivalent to declaring:
// @Configuration, @EnableAutoConfiguration and @ComponentScan.

//Make the application executable:
//Although it is possible to package this service as a traditional WAR file for deployment
// to an external application server, the simpler approach demonstrated below creates a standalone application.
//
// You package everything in a single, executable JAR file, driven by a good old Java main() method.
// Along the way, you use Spring’s support for embedding the Tomcat servlet container as the HTTP runtime,
// instead of deploying to an external instance.



@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
