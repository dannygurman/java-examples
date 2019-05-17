package examples.rxjava.producers.factorymethods.deferVsCreate.ex4;


import io.reactivex.Observable;

public class SomeTypeUsingDeferComplexTest {

    public static void main(String[] args) {
        String value = "Some Value";
        SomeTypeUsingDeferComplex instance = new SomeTypeUsingDeferComplex();
        Observable<SomeEntity> someEntityObservable = instance.createSomeEntityObservable(value);

        someEntityObservable.subscribe(System.out::println)  ;
    }
    //Writing Some Valueto disk
    //SomeEntity{value='Some Value'}
}
