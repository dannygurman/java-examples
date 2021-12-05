package benchmarking.systemtime;

import java.util.concurrent.TimeUnit;

public class systemTimeExample {

    public static void main(String[] args) {
        measureNanoTime();
        measureMilisecTime();
    }

    public static void measureNanoTime()  {
        long startTime = System.nanoTime();

        methodToTime();   //Measure execution time for this method

        long endTime = System.nanoTime();

        long durationInNano = (endTime - startTime);  //Total execution time in nano seconds

        //Same duration in millis

        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(durationInNano);  //Total execution time in nano seconds

        System.out.println(durationInNano);
        System.out.println(durationInMillis);
    }

    public static void measureMilisecTime()  {
        long startTime = System.currentTimeMillis();

        methodToTime();   //Measure execution time for this method

        long endTime = System.currentTimeMillis();

        long durationInMillis = (endTime - startTime);
        //Same duration in millis
        System.out.println(durationInMillis);
    }

    private static void methodToTime() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
