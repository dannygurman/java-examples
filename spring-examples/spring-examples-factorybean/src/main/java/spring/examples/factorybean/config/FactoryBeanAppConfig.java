package spring.examples.factorybean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.examples.factorybean.factories.NonSingleToolFactory;
import spring.examples.factorybean.factories.SingleToolFactory;
import spring.examples.factorybean.factories.ToolFactory;

@Configuration
public class FactoryBeanAppConfig {

    @Bean(name = "tool")
    public ToolFactory toolFactory() {
        ToolFactory factory = new ToolFactory();
        factory.setFactoryId(1);
        factory.setToolId(11);
        return factory;
    }

    @Bean(name = "tool2")
    public NonSingleToolFactory nonSingleToolFactory() {
        NonSingleToolFactory factory = new NonSingleToolFactory();
        factory.setFactoryId(2);
        factory.setToolId(12);
        return factory;
    }

    @Bean(name = "tool3")
    public SingleToolFactory singleToolFactory() {
        SingleToolFactory factory = new SingleToolFactory();
        factory.setFactoryId(3);
        factory.setToolId(13);
        return factory;
    }
}
