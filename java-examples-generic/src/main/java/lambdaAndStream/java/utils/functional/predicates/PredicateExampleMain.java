package lambdaAndStream.java.utils.functional.predicates;

/**
 * Created by dannyg on 11/5/2017.
 */
public class PredicateExampleMain {

    public static void main(String[] args) {
        PredicateExample < String> p1 = s -> s.length() < 20;

        boolean b = p1.test("Hello");
        System.out.println("Hello is shorter then 20 chars" + b);

        PredicateExample < String> p2 = s -> s.length() >5;

        PredicateExample <String > p3 = p1.and(p2);

        System.out.println("P3 for YES: " + p3.test ("YES"));
        System.out.println("P3 for Good morning: " + p3.test ("Good morning"));

        PredicateExample <String > p4 = p1.or(p2);
        System.out.println("P4 for YES: " + p4.test ("YES"));
        System.out.println("P4 for Good morning: " + p4.test ("Good morning"));


        PredicateExample <String > p5 = PredicateExample.isEqualsTo("YES");
        System.out.println("P5 for YES: " + p5.test ("YES"));
        System.out.println("P5 for NO: " + p5.test ("NO"));

        PredicateExample <Integer > p6 = PredicateExample.isEqualsTo(1);
        System.out.println("P6 for 1: " + p6.test (1));

    }
}
