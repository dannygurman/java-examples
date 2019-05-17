package examples.rxjava.producers.factorymethods.deferVsCreate;


import io.reactivex.Observable;

public abstract class SomeType {


    protected  String value;

    public void setValue(String value) {
        this.value = value;
    }

    public abstract Observable<String> valueObservable();
}
