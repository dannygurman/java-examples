package com.springexamples.factorybean.factories;

import com.springexamples.factorybean.Tool;
import org.springframework.beans.factory.FactoryBean;

public abstract class AbsToolFactoryBean2 implements FactoryBean<Tool> {
   
    private int factoryId;
    private int toolId;

    @Override
    public abstract boolean isSingleton();

    @Override
    public Tool getObject() throws Exception {
        return new Tool(toolId);
    }

    @Override
    public Class<?> getObjectType() {
        return Tool.class;
    }



    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }
}
