package examples.rxjava.utils;

import io.reactivex.Observable;

import java.util.List;


//https://www.baeldung.com/rxjava-backpressure
public class ComputeFunction {

    public static void compute(Integer v) {
        try {
            System.out.println("compute integer v: " + v);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static void computeList(List<Integer> list) {
        try {
            System.out.println("compute integers size: " + list.size());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
