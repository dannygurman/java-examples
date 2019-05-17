package inheritance.InheritanceWithExceptiom;

public class B extends  A {

    @Override  //could reduce exception
    public void method_X (){

    }

    @Override  // could throw run time exception
    public void method_y () throws RuntimeException {

    }

    @Override   //could throw RuntimeException (when super do no throe exception
    public void method_z ()  throws RuntimeException {

    }


   /* will not compile - can increase exception level
    public void method_z () throws Exception {

    }*/
}
