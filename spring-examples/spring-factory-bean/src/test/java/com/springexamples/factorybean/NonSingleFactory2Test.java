package com.springexamples.factorybean;

import com.springexamples.factorybean.config.NonSingleToolFactoryBeanAppConfig2;
import com.springexamples.factorybean.config.SingleToolFactoryBeanAppConfig2;
import com.springexamples.factorybean.factories.NonSingleToolFactory2;
import com.springexamples.factorybean.factories.SingleToolFactory2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

@ContextConfiguration(classes = NonSingleToolFactoryBeanAppConfig2.class)
public class NonSingleFactory2Test extends AbsFactoryBeanTest {

    @Resource(name = "&"+FactoryExampleConsts.NON_SINGLE_FACTORY_2)
    private NonSingleToolFactory2 nonSingleToolFactory2;

    @Autowired
    private Tool tool1;
    @Autowired
    private Tool tool2;

    @Test
    public void testSingleToolFactory2() {
        checkFactoryAndTools(nonSingleToolFactory2.getFactoryId(), tool1, tool2, false);
    }
    }
