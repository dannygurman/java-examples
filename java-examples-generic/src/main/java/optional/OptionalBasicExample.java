package optional;

import java.util.Optional;
/**
 * Created by dannyg on 6/20/2017.
 * Advantages of Java 8 Optional:
 Null checks are not required.
 No more NullPointerException at run-time.
 We can develop clean and neat APIs.
 No more Boiler plate code

 Optional.ofNullable() method returns a Non-empty Optional if a value present in the given object.
 Otherwise returns empty Optional.

 Optionaal.empty() method is useful to create an empty Optional object.

 *
 */
public class OptionalBasicExample {

    public static void main(String[] args) {

        Optional<String> gender = Optional.of("MALE");

        System.out.println("Non-Empty Optional:" + gender);
        System.out.println("Non-Empty Optional: Gender value : " + gender.get());
        System.out.println("Empty Optional: " + Optional.empty());

        String answer1 = "Yes";
        String answer2 = null;
        System.out.println("ofNullable on Non-Empty Optional: " + Optional.ofNullable(answer1));
        System.out.println("ofNullable on Empty Optional: " + Optional.ofNullable(answer2));

        //No such element exception;
        Optional.ofNullable(answer2).get();

                // java.lang.NullPointerException
        System.out.println("of on Non-Empty Optional: " + Optional.of(answer2));

    }

    //output:Non-Empty Optional:Optional[MALE]
    /*Non-Empty Optional: Gender value : MALE
    Empty Optional: Optional.empty

    ofNullable on Non-Empty Optional: Optional[Yes]
    ofNullable on Empty Optional: Optional.empty

    Exception in thread "main" java.lang.NullPointerException
    at java.util.Objects.requireNonNull(Objects.java:203)
    at java.util.Optional.<init>(Optional.java:96)
    at java.util.Optional.of(Optional.java:108)*/
    //...
}
