package com.springexamples.factorybean.factories;

import com.springexamples.factorybean.Tool;
import org.springframework.beans.factory.config.AbstractFactoryBean;

//no need to set singleton property because default value is true
public class SingleToolFactory extends AbsToolFactoryBean {
    @Override
    public boolean isSingleton() {
        return true;
    }
}
