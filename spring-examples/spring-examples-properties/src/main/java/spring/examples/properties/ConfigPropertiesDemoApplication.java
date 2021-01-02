package spring.examples.properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import spring.examples.properties.config.AdditionalProperties;
import spring.examples.properties.configProperties.ConfigProperties;

@SpringBootApplication
@ComponentScan(basePackageClasses = {ConfigProperties.class, AdditionalProperties.class})
public class ConfigPropertiesDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigPropertiesDemoApplication.class, args);
    }

}