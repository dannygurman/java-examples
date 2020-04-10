package com.springexamples.factorybean;

import com.springexamples.factorybean.config.NonSingleToolFactoryBeanAppConfig;
import com.springexamples.factorybean.factories.NonSingleToolFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;


@ContextConfiguration(classes = NonSingleToolFactoryBeanAppConfig.class)
public class NonSingleFactoryTest extends AbsFactoryBeanTest {

    @Resource(name = "&"+FactoryExampleConsts.NON_SINGLE_FACTORY)
    private NonSingleToolFactory nonSingleToolFactory;

    @Autowired
    private Tool tool1;
    @Autowired
    private Tool tool2;

    @Test
    public void testSingleToolFactory() {
        checkFactoryAndTools(nonSingleToolFactory.getFactoryId(), tool1, tool2, false);
    }
}
