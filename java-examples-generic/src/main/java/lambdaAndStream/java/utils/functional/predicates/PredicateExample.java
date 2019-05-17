package lambdaAndStream.java.utils.functional.predicates;

/**
 * Created by dannyg on 11/5/2017.
 */
@FunctionalInterface
public interface PredicateExample <T> {

    boolean test(T t);

    default PredicateExample <T> and ( PredicateExample <T> other ){
        return t -> test(t) && other.test(t);
    }

    default PredicateExample <T> or ( PredicateExample <T> other ){
        return t -> test(t) || other.test(t);
    }


     static <U> PredicateExample <U> isEqualsTo ( U u) {
        return s -> s.equals( u);
    }
}
