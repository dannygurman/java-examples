package com.springexamples.factorybean;

import com.springexamples.factorybean.config.SingleToolFactoryBeanAppConfig;
import com.springexamples.factorybean.config.SingleToolFactoryBeanAppConfig2;
import com.springexamples.factorybean.factories.SingleToolFactory;
import com.springexamples.factorybean.factories.SingleToolFactory2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

@ContextConfiguration(classes = SingleToolFactoryBeanAppConfig.class)
public class SingleFactoryTest extends AbsFactoryBeanTest {

    @Resource(name = "&"+FactoryExampleConsts.SINGLE_FACTORY)
    private SingleToolFactory singleToolFactory;


    @Autowired
    private Tool tool1;
    @Autowired
    private Tool tool2;

    @Test
    public void testSingleToolFactory() {
        checkFactoryAndTools(singleToolFactory.getFactoryId(),  tool1,  tool2 , true);

    }
}
