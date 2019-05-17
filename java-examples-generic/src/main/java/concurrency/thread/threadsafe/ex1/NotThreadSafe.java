package concurrency.thread.threadsafe.ex1;

public class NotThreadSafe {
    StringBuilder builder = new StringBuilder();

    public void  add(String text){
        this.builder.append(text);
    }

    public String toString() {
        return this.builder.toString();
    }
}
