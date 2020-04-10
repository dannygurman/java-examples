package com.springexamples.factorybean.config;

import com.springexamples.factorybean.FactoryExampleConsts;
import com.springexamples.factorybean.factories.SingleToolFactory;
import com.springexamples.factorybean.factories.SingleToolFactory2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingleToolFactoryBeanAppConfig {
    
    @Bean(name = FactoryExampleConsts.SINGLE_FACTORY)
    public SingleToolFactory singleToolFactory() {
        SingleToolFactory factory = new SingleToolFactory();
        factory.setFactoryId(FactoryExampleConsts.FACTORY_ID);
        factory.setToolId(FactoryExampleConsts.TOOL_ID);
        return factory;
    }


}