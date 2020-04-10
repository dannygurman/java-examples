package com.springexamples.factorybean.factories;

public class NonSingleToolFactory2 extends AbsToolFactoryBean2 {
    @Override
    public boolean isSingleton() {
        return false;
    }
}
