package com.springexamples.factorybean.factories;

import com.springexamples.factorybean.Tool;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public abstract class AbsToolFactoryBean extends AbstractFactoryBean<Tool> {

    private int factoryId;
    private int toolId;

    @Override
    public abstract boolean isSingleton();

    @Override
    public Class<?> getObjectType() {
        return Tool.class;
    }

    @Override
    protected Tool createInstance() throws Exception {
        return new Tool(toolId);
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