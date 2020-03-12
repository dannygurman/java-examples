package hibernateexample;

import hibernateexample.configuration.HibernateConfiguration;
import hibernateexample.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

//https://www.javarticles.com/2016/03/spring-transactional-annotation-example.html

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HibernateConfiguration.class)
@Transactional //- could be on the test level
@Rollback(false)
// @Commit - alternative to  @Rollback(false) - could be on the test level
public class CustomerServiceTests {

    @Autowired
    private CustomerService customerService;

    @BeforeTransaction
    public void beforeTransactionInitData() {
        System.out.println("Before Transaction");
    }

    @Test
    //@Transactional
    public void testAddCustomer() {
        System.out.println("testAddCustomer");
        customerService.getAllCustomers();

    }
}
