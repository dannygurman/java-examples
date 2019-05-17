package lambdaAndStream.java.utils.functional.consumers;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerMain {

    //A Consumer consume an object, and does not return anything

    public static void main(String[] args) {
        Consumer<String> printer = s -> System.out.println(s);
        Consumer <String>  printer2 = System.out::println; //Method reference

        printer.accept("hi");



        BiConsumer<String, String> biConsumer = (x, y) -> {
            System.out.println(x);
            System.out.println(y);
        };

        biConsumer.accept("java2s.com", " tutorials");
    }
}
