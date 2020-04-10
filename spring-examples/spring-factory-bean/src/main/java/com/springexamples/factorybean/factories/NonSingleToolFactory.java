package com.springexamples.factorybean.factories;

import com.springexamples.factorybean.Tool;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public class NonSingleToolFactory extends AbsToolFactoryBean {

    @Override
    public boolean isSingleton() {
        return false;
    }
}
