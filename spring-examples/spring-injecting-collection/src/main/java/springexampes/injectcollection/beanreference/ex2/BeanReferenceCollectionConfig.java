package springexampes.injectcollection.beanreference.ex2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class BeanReferenceCollectionConfig {

    @Bean
    public CollectionContainerBean getCollectionContainerBean(){
        return new CollectionContainerBean();
    }


    @Bean
    @Order(2)
    public SomeBean getElement() {
        return new SomeBean("x");
    }

    @Bean
    @Order(1)
    public SomeBean getAnotherElement() {
        return new SomeBean("y");
    }

    @Bean
    @Order(3)
    public SomeBean getOneMoreElement() {
        return new SomeBean("z");
    }
}
