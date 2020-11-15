package springexamples.aop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springexamples.aop.model.Employee;

@Configuration
@ComponentScan("springexamples.aop")
@Import({
        MyAspectConfig.class
})
public class AppConfiguration {

    @Bean (name = "em1")
    public Employee getEm() {
        return new Employee("em1");
    }
}
