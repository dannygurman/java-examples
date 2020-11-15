package springexamples.aop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springexamples.aop.aspect.EmployeeAfterAspect;
import springexamples.aop.aspect.EmployeeAnnotationAspect;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MyAspectConfig {

   @Bean
    public EmployeeAfterAspect employeeAfterAspect(){
        return new EmployeeAfterAspect();
    }

    @Bean
    public EmployeeAnnotationAspect employeeAnnotationAspect(){
       return new EmployeeAnnotationAspect();
    }
}
