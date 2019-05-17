package lambdaAndStream.ex3;

public class Main {

    public static void main(String[] args) {
        Predicate <String>  p1  = s -> s.length() < 20;
        boolean result = p1.test("Hello");
        System.out.println(result);

        Predicate <String>  p2  = s -> s.length()  > 5;

        Predicate <String> p3 = p1.and(p2);

        System.out.println(p3.test("123"));
        System.out.println(p3.test("Hello 123"));

        Predicate <String> p4 = p1.or(p2);

        System.out.println(p4.test("123"));
        System.out.println(p4.test("Hello 123"));
    }
}
