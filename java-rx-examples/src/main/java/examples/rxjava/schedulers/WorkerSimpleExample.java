package examples.rxjava.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.junit.Assert;

//https://www.baeldung.com/rxjava-schedulers
public class WorkerSimpleExample {

    private  static String result = "";


    public static void main(String[] args) {

        Scheduler scheduler = Schedulers.trampoline();
        Scheduler.Worker worker = scheduler.createWorker();

        worker.schedule(() -> {result += "action";
        });

        Assert.assertTrue(result.equals("action"));

    }
}
