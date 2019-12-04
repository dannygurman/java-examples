package interview.circularArray;

import java.util.Iterator;

public class CircularArrayTest {

    public static void main(String[] args) {
        int size = 4;
        CircularArray ca = new CircularArray(size);
        ca.set(1, 'a');
        ca.set(2, 'b');
        ca.set(3, 'c');
        ca.set(4, 'd');
        ca.set(5, 'e');
        ca.set(6, 'f');

        printAll(ca);
        ca.rotate(2);
        System.out.println("---");
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
