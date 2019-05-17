package examples.reactorcore;

import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by OlegG on 2/5/2017.
 */
public class TimeMeasurement implements AutoCloseable {
    private final Date startTime;
    public TimeMeasurement() {
        startTime = new Date();
    }

    @Override
    public void close() throws Exception {
        long timeTook = new Date().getTime() - startTime.getTime();
        System.out.println("Execution took: " + timeTook +"ms.");
    }
}
