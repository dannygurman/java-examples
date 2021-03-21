package initialization;

/**
 * Created by DannyG on 03/01/2017.
 * The correct order of initialisation is:



 1 Static fields and static blocks of ancestors, in textual order.
 In each class they are initialized in order of appearance.

 2. Static fields and static block of instantiated class.

 3. Instance fields and initialization blocks of ancestors.
 In each class they are initialized in order of appearance.

4.  Constructor of ancestor after initialization of its instance fields.

 5.Instance fields and initialization blocks of the current class. Also in order of appearance.

 6.Constructor of the current class.


 The result here :
 static in x(1)
 static in z(2)
 Y  (3) first- come from X instance variable
 X - (4)Body of X constructor  (Super of Z)
 Y - (5) Instance variable of Z
 Z - (6) body of Z constructor

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
