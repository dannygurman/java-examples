package examples.rxjava.producers.factorymethods.deferVsCreate.ex1;


import io.reactivex.Observable;
import org.junit.Test;

public class SomeTypeUsingJustTest {


    @Test() //Expected - null pointer
    public void givenJustFactoryMethod_whenValueSetOnCreation_thenNoException() {
        SomeTypeUsingJust instance = new SomeTypeUsingJust();
        instance.setValue("Some Value");
        Observable<String> valueObservable = instance.valueObservable();
        valueObservable.subscribe(System.out::println);
    }


    @Test(expected = NullPointerException.class) //Expected - null pointer
    public void givenJustFactoryMethod_whenValueNotSetOnCreation_thenNullPointerException() {
        SomeTypeUsingJust instance = new SomeTypeUsingJust();
        Observable<String> valueObservable = instance.valueObservable();
        instance.setValue("Some Value");
        valueObservable.subscribe(System.out::println);
        //Return null (not some Value!! as expected )
        // since value had yet to be initialized when Observable.just() is called.
        //just(), from(), and other Observable factorymethods tools store the value of data when created, not when subscribed
    }
}
