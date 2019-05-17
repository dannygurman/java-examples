package concurrency.doubleCheckedLocking;

public class FooUseStaticTest {

    public static void main(String[] args) {
        Helper helper = FooUseStatic.getHelper();
        helper.setX(1);
        System.out.println(helper.getX());
    }
}
