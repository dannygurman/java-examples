package spring.examples.factorybean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.examples.factorybean.factories.NonSingleToolFactory;
import spring.examples.factorybean.factories.SingleToolFactory;
import spring.examples.factorybean.factories.ToolFactory;

@Configuration
public class FactoryBeanAppConfig {

    public static final Integer NON_SINGLE_TOOL_1_ID = 11;
    public static final Integer NON_SINGLE_TOOL_2_ID = 12;
    public static final Integer SINGLETONE_TOOL_ID = 13;

    public static final String BEAN_NAME_NON_SINGLE_TOOL_1 = "non_single_tool1";
    public static final String BEAN_NAME_NON_SINGLE_TOOL_2 = "non_single_tool2";
    public static final String BEAN_NAME_SINGLETONE_TOOL_1 = "singletone_tool1";

    @Bean(name = BEAN_NAME_NON_SINGLE_TOOL_1)
    public ToolFactory toolFactory() {
        ToolFactory factory = new ToolFactory();
        factory.setFactoryId(1);
        factory.setToolId(NON_SINGLE_TOOL_1_ID);
        return factory;
    }

    @Bean(name = BEAN_NAME_NON_SINGLE_TOOL_2)
    public NonSingleToolFactory nonSingleToolFactory() {
        NonSingleToolFactory factory = new NonSingleToolFactory();
        factory.setFactoryId(2);
        factory.setToolId(NON_SINGLE_TOOL_2_ID);
        return factory;
    }

    @Bean(name = BEAN_NAME_SINGLETONE_TOOL_1)
    public SingleToolFactory singleToolFactory() {
        SingleToolFactory factory = new SingleToolFactory();
        factory.setFactoryId(3);
        factory.setToolId(SINGLETONE_TOOL_ID);
        return factory;
    }
}
