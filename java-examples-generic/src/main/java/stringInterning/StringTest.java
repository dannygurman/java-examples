package stringInterning;

/**
 * Created by DannyG on 20/01/2015.
 */

/**
 * Because strings are usually plentiful in most applications, Java goes to great lengths to make sure strings perform well.
 * First, strings are immutable, so they don't have to be thread-safe. Because synchronization is expensive, strings are spared that drag on performance.
 *
 * Second, as you might guess, strings specified at compile-time are flyweights—strings that contain the same character sequence are shared.
 *
 * That sharing can greatly reduce memory footprints, and therefore, increase performance.
 */
public class StringTest {
    public static void main(String[] args) {
        String fly = "fly", weight = "weight";
        String fly2 = "fly", weight2 = "weight";

        System.out.println(fly == fly2);       //TRUE -  fly and fly2 refer to the same String instance
        System.out.println(weight == weight2); // weight and weight2 also refer to the same String instance


        //Strings computed at runtime, such as distinctString  are not flyweights by default;
        String distinctString = fly + weight;
        System.out.println(distinctString == "flyweight"); //False - flyweight and "flyweight" do not refer to the same instance

        // force the issue with the String.intern() which returns flyweights for strings computed at runtime.
        String flyweight = (fly + weight).intern();
        System.out.println(flyweight == "flyweight"); // True - The intern() method returns a flyweight
    }
}
