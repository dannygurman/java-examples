package examples.rxjava.map;

import examples.rxjava.ObservableExampleAbs;
import io.reactivex.Observable;
import io.reactivex.functions.Function;


public class FlatMapBasicExample extends ObservableExampleAbs {

    public static void main(String[] args) {
        System.out.println();
        System.out.println("-------FlatMap-----------");



        Function<String, Observable<String> > bookToTitleObservableMapper = bookName -> getTitleObservable(bookName);
        Observable <String> booksNamesObservable = Observable.just("book1", "book2");
        Observable <String> bookTitleObservable  = booksNamesObservable.flatMap(bookToTitleObservableMapper);
        bookTitleObservable.subscribe(onNextStringPrintln);
    }

    //return observable - therefore flattmap is required
    public static Observable<String> getTitleObservable(String bookName) {
        return Observable.fromArray(new String [] { bookName +"-title" });
    }

/*-------FlatMap-----------
    book1-title
    book2-title*/
}
