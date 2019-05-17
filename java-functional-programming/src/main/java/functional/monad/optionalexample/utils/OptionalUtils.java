package functional.monad.optionalexample.utils;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class OptionalUtils {
//See matching test

    public static <T, U, R>  Optional<R> flatMap2(Optional<T> opt1OfT, Optional<U> opt2Ofu, BiFunction<T, U, Optional<R>> inputMapper) {

         Function< T , Optional <R> > tToOptionalRMapper = t ->  {

            Function< U , Optional <R> > uToOptionalRMapper = u -> inputMapper.apply(t, u);

            Optional<R> optOfRInternal =  opt2Ofu.flatMap(uToOptionalRMapper);
            return optOfRInternal;
        };


        Optional<R> rOptional =  opt1OfT.flatMap(tToOptionalRMapper);

        return rOptional;
    }

    public static <T, U, R> Optional<R> flatMap2ShortVersion(Optional<T> opt1OfT, Optional<U> opt2Ofu, BiFunction<T, U, Optional<R>> mapper) {
        Optional<R> rOptional = opt1OfT.flatMap(t -> opt2Ofu.flatMap(u -> mapper.apply(t, u)));
        return rOptional;
    }

}
