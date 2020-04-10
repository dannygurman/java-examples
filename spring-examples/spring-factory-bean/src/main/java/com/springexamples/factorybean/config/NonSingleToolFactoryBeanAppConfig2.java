package com.springexamples.factorybean.config;

import com.springexamples.factorybean.FactoryExampleConsts;
import com.springexamples.factorybean.factories.NonSingleToolFactory2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NonSingleToolFactoryBeanAppConfig2 {

    @Bean(name = FactoryExampleConsts.NON_SINGLE_FACTORY_2)
    public NonSingleToolFactory2 nonSingleToolFactory2() {
        NonSingleToolFactory2 factory = new NonSingleToolFactory2();
        factory.setFactoryId(FactoryExampleConsts.FACTORY_ID);
        factory.setToolId(FactoryExampleConsts.TOOL_ID);
        return factory;
    }
}
