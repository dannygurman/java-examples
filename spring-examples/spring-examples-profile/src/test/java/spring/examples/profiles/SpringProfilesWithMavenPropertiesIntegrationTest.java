package spring.examples.profiles;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import spring.examples.profiles.config.DevDatasourceConfig;
import spring.examples.profiles.config.MyDatasourceConfig;
import spring.examples.profiles.config.MySpringProfilesConfig;

/**
 * Check :
 * 1.<profiles> in pom.xml!
 * 2. <filtering> in <resource>in pom.xml
 * 3.See also spring.profiles.active=@spring.profiles.active@
 * in application.properties
 * 4. mvn clean package -Pprod
 * This command will package the application for prod profile.
 * Note !! - in intelliJ maven profile check box will be added - see pic
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MySpringProfilesConfig.class }, loader = AnnotationConfigContextLoader.class)
public class SpringProfilesWithMavenPropertiesIntegrationTest {

    @Autowired
    MyDatasourceConfig datasourceConfig;

    @Test
    public void setupDatasource() {
        Assert.assertTrue(datasourceConfig instanceof DevDatasourceConfig);
    }
}

