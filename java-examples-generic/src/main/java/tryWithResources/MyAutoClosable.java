package tryWithResources;

public class MyAutoClosable implements  AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("MyAutoClosable closed!");
    }

   // The doIt() method is not part of the AutoClosable interface.
   //
   // It is there because we want to be able to do something more than just closing the object.
    public void doIt() {
        System.out.println("MyAutoClosable doing it!");
    }


}
