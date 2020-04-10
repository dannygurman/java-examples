package com.springexamples.factorybean.factories;

public class SingleToolFactory2 extends AbsToolFactoryBean2 {

    @Override
    public boolean isSingleton() {
        return true;
    }
}
