package initialization;

/**
 * Created by DannyG on 03/01/2017.
 */
public class X {
    static {
        System.out.println("static in x");
    }
    private Y b = new Y();

    X() {
        System.out.println("X");
    }
}
