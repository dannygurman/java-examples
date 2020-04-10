package com.springexamples.factorybean;

import com.springexamples.factorybean.config.SingleToolFactoryBeanAppConfig2;
import com.springexamples.factorybean.factories.SingleToolFactory2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

@ContextConfiguration(classes = SingleToolFactoryBeanAppConfig2.class)
public class SingleFactory2Test extends AbsFactoryBeanTest {

    @Resource(name = "&"+FactoryExampleConsts.SINGLE_FACTORY_2)
    private SingleToolFactory2 singleToolFactory2;


    @Autowired
    private Tool tool1;
    @Autowired
    private Tool tool2;

    @Test
    public void testSingleToolFactory2() {
        checkFactoryAndTools(singleToolFactory2.getFactoryId(),  tool1,  tool2 , true);

    }
}