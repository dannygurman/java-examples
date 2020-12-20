package springexamples.boottest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

    @Autowired
    private GreetingController controller;

    @Test
    public void contexLoadsTest() throws Exception {
        assertThat(controller).isNotNull();
    }

    //A nice feature of the Spring Test support is that the application context is cached in between tests,
    // so if you have multiple methods in a test case, or multiple test cases with the same configuration,
    // they only incur the cost of starting the application once.

    // You can control the cache using the @DirtiesContext annotation.
}
