package examples.rxjava.scan;

import examples.rxjava.ObservableExampleAbs;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;


public class ScanBasicExample extends ObservableExampleAbs {

    public static void main(String[] args) {
        System.out.println("--------Scan----------");

        StringBuilder initialValue = new StringBuilder();
        BiFunction<StringBuilder, String, StringBuilder> accumulator =   (sb , str) -> sb.append(str) ;//StringBuilder::append

        Observable.fromArray(letters)
                .scan(initialValue, accumulator)
                .subscribe(onNextStringBuilderPrintln);

        /*Output:
        a
        ab
        abc abcd
        abcde
        abcdef
        abcdefg
        abcdefgh
        abcdefghi*/
    }

}
