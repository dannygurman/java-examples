package misc.multiply;

import java.util.concurrent.*;

public class MultiplyTest {

    private int score = 0;
    private int timeoutSec = 5;
    private int min =1;
    private int max = 10;

    private int getNum() {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }


    public void ask () throws InterruptedException {
        int num1 = getNum();
        int num2 = getNum();
        int result = num1 * num2;
        System.out.println("" + num1 + " * " + num2 + "  = ?");
        String answer = readLine();
    }

    public String readLine() throws InterruptedException {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        String input = null;
        try {
            Future<String> result = ex.submit(new ConsoleInputReadTask());
            try {
                input = result.get(timeoutSec, TimeUnit.SECONDS);
            } catch (ExecutionException e) {
                e.getCause().printStackTrace();
            } catch (TimeoutException e) {
                System.out.println("TIMEOUT");
                result.cancel(true);
            }
        } finally {
            ex.shutdownNow();
        }
        return input;
    }

    public static void main(String[] args) throws InterruptedException{
        MultiplyTest multiplyTest = new MultiplyTest();
        String x = multiplyTest.readLine();
        System.out.println("x"+x);

    }


}
