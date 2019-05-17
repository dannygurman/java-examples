package tryWithResources;

import org.junit.Test;

public class MyAutoClosableTest {


    @Test
    public  void usingMyAutoClosable() throws Exception {

        try(MyAutoClosable myAutoClosable = new MyAutoClosable()){
            myAutoClosable.doIt();
        }
    }

    //Outpur
    //MyAutoClosable doing it!
   // MyAutoClosable closed!
}
