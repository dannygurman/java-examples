package spring.examples.factorybean.factories;

import lombok.Data;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import spring.examples.factorybean.model.Tool;

@Data
public class NonSingleToolFactory extends AbstractFactoryBean<Tool> {
    
    private int factoryId;
    private int toolId;

    public NonSingleToolFactory() {
        setSingleton(false);
    }

    @Override
    public Class<?> getObjectType() {
        return Tool.class;
    }

    @Override
    protected Tool createInstance() throws Exception {
        return new Tool(toolId);
    }

}
