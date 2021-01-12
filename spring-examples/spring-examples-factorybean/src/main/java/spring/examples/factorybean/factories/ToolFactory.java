package spring.examples.factorybean.factories;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;
import spring.examples.factorybean.model.Tool;

@Data
public class ToolFactory implements FactoryBean<Tool> {
   
    private int factoryId;
    private int toolId;

    @Override
    public Tool getObject() throws Exception {
        return new Tool(toolId);
    }

    @Override
    public Class<?> getObjectType() {
        return Tool.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}
