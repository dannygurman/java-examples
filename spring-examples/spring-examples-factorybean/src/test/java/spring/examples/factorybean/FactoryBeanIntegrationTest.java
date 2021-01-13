package spring.examples.factorybean;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static spring.examples.factorybean.config.FactoryBeanAppConfig.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.examples.factorybean.config.FactoryBeanAppConfig;
import spring.examples.factorybean.model.Tool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FactoryBeanAppConfig.class)
public class FactoryBeanIntegrationTest {

    @Resource(name = BEAN_NAME_NON_SINGLE_TOOL_1)
    private Tool tool_non_single_1_1;

    @Resource(name = BEAN_NAME_NON_SINGLE_TOOL_1)
    private Tool tool_non_single_1_2;

    @Resource(name = BEAN_NAME_NON_SINGLE_TOOL_2)
    private Tool tool_non_single_2_1;

    @Resource(name = BEAN_NAME_NON_SINGLE_TOOL_2)
    private Tool tool_non_single_2_2;

    @Resource(name = BEAN_NAME_SINGLETONE_TOOL_1)
    private Tool tool_single_1;

    @Resource(name = BEAN_NAME_SINGLETONE_TOOL_1)
    private Tool tool_single_2;

    @Test
    public void testNonSingleToolFactory1() {
        assertThat(tool_non_single_1_1.getId(), equalTo(NON_SINGLE_TOOL_1_ID));
        assertThat(tool_non_single_1_2.getId(), equalTo(NON_SINGLE_TOOL_1_ID));
        assertTrue(tool_non_single_1_1 != tool_non_single_1_2);
    }

    @Test
    public void testNonSingleToolFactory2() {
        assertThat(tool_non_single_2_1.getId(), equalTo(NON_SINGLE_TOOL_2_ID));
        assertThat(tool_non_single_2_2.getId(), equalTo(NON_SINGLE_TOOL_2_ID));
        assertTrue(tool_non_single_2_1 != tool_non_single_2_2);
    }

    @Test
    public void testSingleToolFactory() {
        assertThat(tool_single_1.getId(), equalTo(SINGLETONE_TOOL_ID));
        assertThat(tool_single_2.getId(), equalTo(SINGLETONE_TOOL_ID));
        assertTrue(tool_single_1 == tool_single_2);
    }
}
