package functional.monad.optionalexample.add;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

//https://medium.com/@afcastano/monads-for-java-developers-part-1-the-optional-monad-aa6e797b8a6e
//https://medium.com/@afcastano/monads-for-java-developers-part-2-the-result-and-log-monads-a9ecc0f231bb

//Technically, a monad is a parameterised type such as Optional and Stream in Java which:
 //Implements flatMap (a.k.a. bind)
// and unit (a.k.a. identity, return, Optional.of(), etc…).

//For the sake of aligning the terminology, let’s say that a parameterised type such as Optional<String>
// has a type parameter: String
// and a wrapper type: Optional.
// In this case we say that “a String type is wrapped by an Optional context”.

public class OptionalAddExample {

    public static void main(String[] args) {
        Optional <Integer> val1 = Optional.of(1);
        Optional <Integer> val2 = Optional.of(2);

        //Optional<Integer> result = optionalAddNoMapper(val1 , val2);
        //Optional<Integer> result = optionalAdd(val1 , val2);
        Optional<Integer> result = optionalAddVerbose(val1 , val2);

        System.out.println(result.get());
    }


    //"Classic"" way -

    // /Notice how we need to manually “unwrap the Integer from the Optional context”
    // by checking whether the numbers are present ), and doing something with the empty case .

    public static Optional<Integer> optionalAddNoMapper(Optional<Integer> val1, Optional<Integer> val2) {
        //val1.get()- unwrapping the parameter from the Optional context
        if(val1.isPresent() && val2.isPresent()) {
            return Optional.of(val1.get() + val2.get());
        }

        return Optional.empty();
    }


    //This is precisely what monads helps us to do:
    // To avoid dealing with the context when composing parametrised types.
    // In the next example, the context refers to the Optional context, and composition refers to addition, so we can say:
    // The Optional monad help us to avoid dealing with the Optional context when adding Optional types.

    // The flatMap method let us take the parameter from the monad and operate with it to produce another
    // Monad of the same type.
    // Because of this signature, we can nest flatMap calls on different   monads to compose their parameters.

    // / Also, Optional.of (a.k.a unit) is a very handy method that will let us take any value and produce another Optional


  //  The Optional monad via its flatMap method properly unwraps the parameter for us.
  // It won’t invoke the second flatMap mapper function if the first Optional is empty (see flatMap implementation below).
  // In fact, if any of the Optionals involved in the composition is empty, the result will be an empty Optional.
    public static Optional<Integer> optionalAdd(Optional<Integer> val1, Optional<Integer> val2) {
        return val1.flatMap( first ->
                val2.flatMap( second ->
                        Optional.of(first + second)
                ));
    }

    public static Optional<Integer> optionalAddVerbose(Optional<Integer> val1, Optional<Integer> val2) {

        Function<Integer ,Optional<Integer>> firstValMapper = first -> {
            Function<Integer , Optional<Integer> > secondValMapper = second -> Optional.of(first + second);
            Optional<Integer> result =  val2.flatMap( secondValMapper);
            return result;
        };


        Optional<Integer> result =  val1.flatMap(firstValMapper);

        return result;
    }


   // From flatMap:
  /*  public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }*/
}
