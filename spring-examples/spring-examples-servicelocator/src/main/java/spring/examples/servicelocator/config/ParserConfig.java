package spring.examples.servicelocator.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.examples.servicelocator.parsers.ContentType;

@Configuration
public class ParserConfig {

  @Bean("parserFactory")
  public FactoryBean serviceLocatorFactoryBean() {
    ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
    factoryBean.setServiceLocatorInterface(ContentType.ParserFactory.class);
    return factoryBean;
  }
}
