package lambdaAndStream.filterCollect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dannyg on 6/29/2017.
 */
public class BeforeJava8 {

    public static void main(String[] args) {

        List<String> lines = Arrays.asList("spring", "node", "mkyong");
        List<String> result = getFilterOutput(lines, "mkyong");
        for (String temp : result) {
            System.out.println(temp);    //output : spring, node
        }

    }

    private static List<String> getFilterOutput(List<String> lines, String filter) {
        List<String> result = new ArrayList<>();
        for (String line : lines) {
            if (!"mkyong".equals(line)) { // we dont like mkyong
                result.add(line);
            }
        }
        return result;
    }
//Output:
    //spring
     //  node
}
