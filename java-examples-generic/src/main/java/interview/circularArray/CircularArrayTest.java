package interview.circularArray;

import java.util.Iterator;

public class CircularArrayTest {

    public static void main(String[] args) {
        int size = 4;
        CircularArray ca = new CircularArray(size);
        ca.set(0, 'a');
        ca.set(1, 'b');
        ca.set(2, 'c');
        ca.set(3, 'd');
        System.out.println("- 1 --");
        printAll(ca);

        ca.set(4, 'e');
        System.out.println("- 2 --");
        printAll(ca);

        ca.set(5, 'f');
        System.out.println("- 3 --");
        printAll(ca);

        ca.rotate(2);
        System.out.println("- 4 --");
        printAll(ca);

        }

        private static void printAll(CircularArray ca){
            Iterator caIter = ca.iterator();
           /* while (caIter.hasNext()){
                System.out.println(caIter.next());
            }*/
            for ( ;caIter.hasNext(); ) {
                System.out.println(caIter.next());
            }
        }
}
