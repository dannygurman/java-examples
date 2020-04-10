package springexampes.injectcollection.beanreference.ex2;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CollectionContainerBean {

    //let's consider a scenario when there is not a SomeBean.
    // If there isn't a SomeBean registered in the application context, Spring will throw an exception
    // because the required dependency is missing.    //
    //We can use @Autowired(required = false) to mark the dependency as optional.
    // Instead of throwing an exception, the SomeBean won't be initialized and its value will stay null.
    @Autowired(required = false)
    private List<SomeBean> beanList;

    public void printBeanList() {
        for (SomeBean someBean: beanList){
            System.out.println(someBean.getName());
        }

    }
}
