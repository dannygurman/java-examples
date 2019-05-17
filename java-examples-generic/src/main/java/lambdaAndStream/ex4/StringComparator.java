package lambdaAndStream.ex4;


import java.util.Arrays;
import java.util.Comparator;

public class StringComparator {

    static String [] strings = {"ab" , "dff" , "aaaa" , "dfdgfdfgf"};
    static Comparator<String> stringLengthComparator  = (s1 , s2) ->  Integer.compare(s1.length() , s2.length());

    public static void main(String[] args) {
        Arrays.sort(strings , stringLengthComparator);

        System.out.println(Arrays.toString(strings ));
    }



}
