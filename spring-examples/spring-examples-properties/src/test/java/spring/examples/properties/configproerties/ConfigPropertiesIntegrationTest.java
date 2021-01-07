package spring.examples.properties.configproerties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import spring.examples.properties.ConfigPropertiesDemoApplication;
import spring.examples.properties.config.AdditionalProperties;
import spring.examples.properties.configProperties.ConfigProperties;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ConfigPropertiesDemoApplication.class})
@TestPropertySource(locations = {"classpath:configprops-test.properties"})
public class ConfigPropertiesIntegrationTest {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private AdditionalProperties additionalProperties;

    @Test
    public void whenSimplePropertyQueriedthenReturnsProperty()  {
        String from = configProperties.getFrom();
        System.out.println("from:"+from);
        Assert.assertTrue("From address is read as null!", from != null);
    }

    @Test
    public void whenListPropertyQueriedThenReturnsProperty() throws Exception {
        List<String> properties =  configProperties.getDefaultRecipients();
        Assert.assertTrue("Couldn't bind list property!", properties != null);
        Assert.assertTrue("Incorrectly bound list property. Expected 2 entries!", properties.size() == 2);
    }

    @Test
    public void whenMapPropertyQueriedthenReturnsProperty() throws Exception {
        Map<String, String> additionalHeaders =  configProperties.getAdditionalHeaders();
        Assert.assertTrue("Couldn't bind map property!", additionalHeaders != null);
        Assert.assertTrue("Incorrectly bound map property. Expected 3 Entries!", additionalHeaders.size() == 3);
    }

    @Test
    public void whenObjectPropertyQueriedthenReturnsProperty() throws Exception {
        ConfigProperties.Credentials credentials = configProperties.getCredentials();
        Assert.assertTrue("Couldn't bind map property!", credentials != null);
        Assert.assertTrue("Incorrectly bound object property!", credentials.getAuthMethod().equals("SHA1"));
        Assert.assertTrue("Incorrectly bound object property!", credentials.getUsername().equals("john"));
        Assert.assertTrue("Incorrectly bound object property!", credentials.getPassword().equals("password"));
    }

    @Test
    public void whenAdditionalPropertyQueriedthenReturnsProperty() {
        Assert.assertTrue(additionalProperties.getUnit().equals("km-test"));
        Assert.assertTrue(additionalProperties.getMax() == 12344321);
    }
}