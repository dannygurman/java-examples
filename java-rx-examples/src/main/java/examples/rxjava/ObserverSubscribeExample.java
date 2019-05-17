package examples.rxjava;


import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ObserverSubscribeExample {

    public static void main(String[] args) {

        ObservableOnSubscribe<String> onSubscribe = (subscriber) -> {
            System.out.println("\n");
            for (int i =1 ; i <= 3 ; i++) {
                System.out.println("In onSubscribe - calling subscriber.onNext with 'Hello World "+ i + " String");
                subscriber.onNext("Hello World "+ i);
            }
            System.out.println("In onSubscribe - calling subscriber.onCompleted");
            subscriber.onComplete();
        };



        Observable<String> observervable = Observable.create(onSubscribe);





        Observer<String> myObserver = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable val) {
                System.out.println("\n myObserver onSubscribe ");
            }

            @Override
            public void onNext(String s) {
                System.out.println("\nMyObserver onNext(): " + s);
            }

            @Override
            public void onComplete() {
                System.out.println("\nObserver completed");
            }

            @Override
            public void onError(Throwable e) {
            }
        };

        System.out.print("\nSubscribing mySubscriber");

        observervable.subscribe(myObserver);

    }

}
