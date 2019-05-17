package examples.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;



public abstract class ObservableExampleAbs {

    protected static Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    protected  static String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};

  protected static Function<String,  String> upperCaseFunction =  String::toUpperCase;

    protected static Consumer<String> onNextStringPrintln = System.out::println;

    protected static Consumer<Integer> onNextIntegerPrintln = System.out::println;

   protected static Consumer<StringBuilder> onNextStringBuilderPrintln = System.out::println;



   protected static Observer<Integer> intObserver = new Observer<Integer>() {
       @Override
       public  void onSubscribe(Disposable var1) {
           System.out.println("onSubscribe");
       }

        @Override
        public void onComplete() {
            System.out.println("onComplete");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("onError. Message:" + e.getMessage());
        }

        @Override
        public void onNext(Integer integer) {
            System.out.println("onNext: "+ integer);
        }
    };


}
