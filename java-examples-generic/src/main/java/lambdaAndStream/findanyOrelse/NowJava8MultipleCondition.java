package lambdaAndStream.findanyOrelse;

/**
 * Created by dannyg on 6/29/2017.
 */
import lambdaAndStream.Person;

import java.util.Arrays;
import java.util.List;

public class NowJava8MultipleCondition  {

    public static void main(String[] args) {

        List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );

        Person result1 = persons.stream()
                .filter((p) -> "jack".equals(p.getName()) && 20 == p.getAge())
                .findAny()
                .orElse(null);

        System.out.println("result 1 :" + result1);

        //or like this
        Person result2 = persons.stream()
                .filter(p -> {
                    if ("jack".equals(p.getName()) && 20 == p.getAge()) {
                        return true;
                    }
                    return false;
                }).findAny()
                .orElse(null);

        System.out.println("result 2 :" + result2);

    }
//Output:
   // result 1 :Person{name='jack', age=20}
  //  result 2 :Person{name='jack', age=20}

}