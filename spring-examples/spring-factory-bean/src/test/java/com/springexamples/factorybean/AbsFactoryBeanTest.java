package com.springexamples.factorybean;

import com.springexamples.factorybean.config.SingleToolFactoryBeanAppConfig2;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbsFactoryBeanTest {

    protected  void  checkFactoryAndTools(int factoryId , Tool tool1, Tool tool2 , boolean isSingle) {
        assertThat(factoryId, equalTo(FactoryExampleConsts.FACTORY_ID));
        assertThat(tool1.getId(), equalTo(FactoryExampleConsts.TOOL_ID));
        assertThat(tool2.getId(), equalTo(FactoryExampleConsts.TOOL_ID));
        if (isSingle) {
            assertTrue(tool1 == tool2);
        } else {
            assertTrue(tool1 != tool2);
        }

    }
}
