package spring.examples.profiles;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import spring.examples.profiles.config.DevDatasourceConfig;
import spring.examples.profiles.config.MyDatasourceConfig;
import spring.examples.profiles.config.MySpringProfilesConfig;

import static spring.examples.profiles.config.ProfileConsts.DEVELOPMENT_PROFILE;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(DEVELOPMENT_PROFILE)
@ContextConfiguration(classes = { MySpringProfilesConfig.class }, loader = AnnotationConfigContextLoader.class)
public class DevProfileWithAnnotationIntegrationTest {
    @Autowired
    MyDatasourceConfig datasourceConfig;

    @Test
    public void testSpringProfiles() {
        Assert.assertTrue(datasourceConfig instanceof DevDatasourceConfig);
    }


}
