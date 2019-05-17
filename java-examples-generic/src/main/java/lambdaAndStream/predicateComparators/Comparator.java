package lambdaAndStream.predicateComparators;

import java.util.function.Function;

/**
 * Created by dannyg on 9/12/2017.
 */

@FunctionalInterface
public interface  Comparator <T> {

    //return int following order o
     int compare (T t1 , T t2);

     //If result as equal - use result of  comparator used as parameter
      default Comparator <T> thenComparing (Comparator <T> cmp) {

         return (p1 , p2 ) -> {
             if (compare(p1, p2) == 0) {
                 return cmp.compare(p1, p2);
             } else {
                 return compare(p1, p2);
             }
         };
     }


    default Comparator <T> thenComparing (Function <T , Comparable> f) {
       Comparator <T> cmp = comparing(f);
        return thenComparing (cmp);
    }

      static Comparator <Person > comparingPersonInt(Function <Person , Integer> f) {
         return (p1 , p2) -> f.apply(p1) - f.apply(p2);
     }

    static Comparator <Person > comparingPersonString(Function <Person , String> f) {
        return (p1 , p2) -> f.apply(p1).compareTo(f.apply(p2));
    }
     //General function - the recommended
     static Comparator <Person > comparingPerson(Function <Person , Comparable> f) {
         return (p1 , p2) -> f.apply(p1).compareTo(f.apply(p2));
     }

    static <U> Comparator <U> comparing(Function <U , Comparable> f) {
        return (p1, p2) -> f.apply(p1).compareTo(f.apply(p2));
    }

    }
