package springexamples.aop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springexamples.aop.aspect.*;

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

    @Bean
    public EmployeeAroundAspect employeeAroundAspect(){
       return new EmployeeAroundAspect();
    }

    @Bean
    public EmployeeAspect employeeAspect(){
       return new EmployeeAspect();
    }

    @Bean
    EmployeeAspectJoinPoint employeeAspectJoinPoint(){
      return new EmployeeAspectJoinPoint();
    }

    @Bean
    EmployeeAspectPointcut employeeAspectPointcut(){
       return new EmployeeAspectPointcut();
    }

}
