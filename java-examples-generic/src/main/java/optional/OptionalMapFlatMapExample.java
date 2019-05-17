package optional;

import java.util.Optional;
/**
 * Created by dannyg on 6/20/2017.
 */
public class OptionalMapFlatMapExample {
    public static void main(String[] args) {

        Optional<String> nonEmptyGender = Optional.of("male");
        Optional<String> emptyGender = Optional.empty();

        System.out.println("Non-Empty Optional:: " + nonEmptyGender.map(String::toUpperCase));
        System.out.println("Empty Optional    :: " + emptyGender.map(String::toUpperCase));

        Optional<Optional<String>> nonEmptyOptionalGender = Optional.of(Optional.of("male"));
        System.out.println("Optional value   :: " + nonEmptyOptionalGender);
        System.out.println("Optional.map     :: " + nonEmptyOptionalGender.map(gender -> gender.map(String::toUpperCase)));
        System.out.println("Optional.flatMap :: " + nonEmptyOptionalGender.flatMap(gender -> gender.map(String::toUpperCase)));

        Optional<String> flatMap =  nonEmptyOptionalGender.flatMap(gender -> gender.map(String::toUpperCase));
        Optional  <Optional <String> > map =  nonEmptyOptionalGender.map(gender -> gender.map(String::toUpperCase));
        map.isPresent();
    }
  /*
    Output:
    Non-Empty Optional:: Optional[MALE]
    Empty Optional    :: Optional.empty
    Optional value   :: Optional[Optional[male]]
    Optional.map     :: Optional[Optional[MALE]]
    Optional.flatMap :: Optional[MALE]*/
}
