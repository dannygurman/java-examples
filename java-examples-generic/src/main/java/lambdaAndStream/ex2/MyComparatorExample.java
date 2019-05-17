package lambdaAndStream.ex2;

/**
 * Created by dannyg on 6/25/2017.
 *
 * Lambdas as Objects
 A Java lambda expression is essentially an object.
 You can assign a lambda expression to a variable and pass it around,
 like you do with any other object. Here is an example:
 */
public class MyComparatorExample {

    public void example ()  {
        MyComparator myComparator = (a1, a2) -> {return a1 > a2 ;} ;
        boolean result = myComparator.compare(2, 5);
        System.out.print(result);

        Runnable r = () -> {};
    }

}
