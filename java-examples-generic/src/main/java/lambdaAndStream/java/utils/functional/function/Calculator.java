package lambdaAndStream.java.utils.functional.function;

import java.util.function.BiFunction;

/**
 * Created by dannyg on 11/5/2017.
 */
public class Calculator {

    public String calc (BiFunction<Integer, Integer, String> bi, Integer i1, Integer i2) {
        return bi.apply(i1, i2);
    }
}
