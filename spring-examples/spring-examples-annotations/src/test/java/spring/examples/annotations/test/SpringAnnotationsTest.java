package spring.examples.annotations.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.examples.annotations.MyPrototypeComponent;
import spring.examples.annotations.MySingletoneBean;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringAnnotationsTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private MyPrototypeComponent myPrototypeComponent1;

    @Autowired
    private MyPrototypeComponent myPrototypeComponent2;

    @Autowired
    private MySingletoneBean mySingletoneBean1;

    @Autowired
    private MySingletoneBean mySingletoneBean2;

    @Test
    public final void givenX_thenX() {
        System.out.println(" ---Start x");
        System.out.println("MyPrototypeComponent1 hash:" + myPrototypeComponent1.hashCode());
        System.out.println("MyPrototypeComponent2 hash:" + myPrototypeComponent2.hashCode());
    }

    @Test
    public final void givenXx_thenX() {
        System.out.println(" ---Start x");
        MyPrototypeComponent myPrototypeComponent3 = context.getBean(MyPrototypeComponent.class);
        MyPrototypeComponent myPrototypeComponent4 = context.getBean(MyPrototypeComponent.class);
        System.out.println("MyPrototypeComponent3 hash:" + myPrototypeComponent3.hashCode());
        System.out.println("MyPrototypeComponent4 hash:" + myPrototypeComponent4.hashCode());
    }

    @Test
    public final void givenXd_thenX() {
        System.out.println(" ---Start x");
        System.out.println("mySingletoneBean1 hash:" + mySingletoneBean1.hashCode());
        System.out.println("mySingletoneBean2 hash:" + mySingletoneBean2.hashCode());
    }

    @Test
    public final void givenXds_thenX() {
        System.out.println(" ---Start x");
        MySingletoneBean mySingletoneBean3= context.getBean(MySingletoneBean.class);
        MySingletoneBean mySingletoneBean4 = context.getBean(MySingletoneBean.class);
        System.out.println("mySingletoneBean3 hash:" + mySingletoneBean3.hashCode());
        System.out.println("mySingletoneBean4 hash:" + mySingletoneBean4.hashCode());
    }
}
