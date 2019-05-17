package examples.rxjava.producers.factorymethods.deferVsCreate.ex4;



import io.reactivex.Observable;

import java.io.IOException;
import java.util.concurrent.Callable;

//https://blog.danlew.net/2015/07/23/deferring-observable-code-until-subscription-in-rxjava/
/*
Suppose we want a method which writes data to disk,
 then returns that data. This is one way to do it with defer():

 //we don't want any of the code to execute until subscription.
 */

public class SomeTypeUsingDeferComplex  {


    DbMock db = new DbMock();

    public Observable<SomeEntity> createSomeEntityObservable(String value) {

        Callable<Observable<SomeEntity>> observableFactory = () -> {
            SomeEntity someEntity = new SomeEntity();
            someEntity.setValue(value);

            try {
                db.writeToDisk(someEntity);
            } catch (IOException e) {
                return Observable.error(e);
            }

            return Observable.just(someEntity);
        };


        return Observable.defer(observableFactory);
    }
}
