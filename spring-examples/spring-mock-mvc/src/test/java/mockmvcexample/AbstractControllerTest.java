package mockmvcexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import mockmvcexample.config.MyServiceTestConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

//At its core, the TestContext framework allows you to annotate test classes with @ContextConfiguration to specify which configuration
//files to use to load the ApplicationContext for your test.
//By default the ApplicationContext is loaded using the GenericXmlContextLoader which loads a context
//from XML Spring configuration files.
//You can then access beans from the ApplicationContext by annotating fields in your test class with @Autowired, @Resource, or @Inject.

@ImportAutoConfiguration({MyServiceTestConfig.class})
@ContextConfiguration(classes = {ObjectMapper.class}, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations="classpath:application-test.properties")
@WithUserDetails(userDetailsServiceBeanName = "testUserDetailsService")
public class AbstractControllerTest {

}
