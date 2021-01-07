package spring.examples.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import spring.examples.properties.config.BasicPropertiesWithJavaConfig;
import spring.examples.properties.config.PropertiesWithJavaConfigOther;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BasicPropertiesWithJavaConfig.class,
        PropertiesWithJavaConfigOther.class}, loader = AnnotationConfigContextLoader.class)
public class ExtendedPropertiesWithJavaIntegrationTest {

    @Autowired
    private Environment env;

    @Value("${key.something}")
    private String injectedProperty1;

    @Value("${key.something2}")
    private String injectedProperty2;

    @Test
    public final void givenContextIsInitialized_thenNoException() {
        System.out.println("in test via @Value: " + injectedProperty1);
        System.out.println("in test Environment: " + env.getProperty("key.something"));
    }

    @Test
    public final void givenContextIsInitialized_thenNoException2() {
        System.out.println("in test via @Value: " + injectedProperty2);
        System.out.println("in test Environment: " + env.getProperty("key.something2"));
    }

}
