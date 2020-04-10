package com.springexamples.factorybean.config;

import com.springexamples.factorybean.FactoryExampleConsts;
import com.springexamples.factorybean.factories.NonSingleToolFactory;
import com.springexamples.factorybean.factories.NonSingleToolFactory2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NonSingleToolFactoryBeanAppConfig {

    @Bean(name = FactoryExampleConsts.NON_SINGLE_FACTORY)
    public NonSingleToolFactory nonSingleToolFactory() {
        NonSingleToolFactory factory = new NonSingleToolFactory();
        factory.setFactoryId(FactoryExampleConsts.FACTORY_ID);
        factory.setToolId(FactoryExampleConsts.TOOL_ID);
        return factory;
    }
}
