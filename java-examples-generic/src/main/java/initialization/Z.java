package initialization;

/**
 * Created by DannyG on 03/01/2017.
 * The correct order of initialisation is:

 1. Static variable initialisers and static initialisation blocks, in textual order, if the class hasn't been previously initialised.
2.  The super() call in the constructor, whether explicit or implicit.
3.  Instance variable initialisers and instance initialisation blocks, in textual order.
 4. Remaining body of constructor after super().

 The result here :YXYX
 Y  first- come from X instance variable
 X - Body of X (Super of Z
 Y - Instance variable of Z
 Z - body of Z constructor

 */
public class Z extends X {
    static {
        System.out.println("static in z");
    }
    private Y y = new Y();
    Z() {
        System.out.println("Z");
    }

    public static void main(String[] args) {
        new Z();
    }
}
