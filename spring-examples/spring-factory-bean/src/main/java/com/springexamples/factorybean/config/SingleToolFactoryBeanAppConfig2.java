package com.springexamples.factorybean.config;

import com.springexamples.factorybean.FactoryExampleConsts;
import com.springexamples.factorybean.factories.SingleToolFactory2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingleToolFactoryBeanAppConfig2 {
    
    @Bean(name = FactoryExampleConsts.SINGLE_FACTORY_2)
    public SingleToolFactory2 singleToolFactory2() {
        SingleToolFactory2 factory = new SingleToolFactory2();
        factory.setFactoryId(FactoryExampleConsts.FACTORY_ID);
        factory.setToolId(FactoryExampleConsts.TOOL_ID);
        return factory;
    }


}