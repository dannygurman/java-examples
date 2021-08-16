package algorithms.stack.getMin;

public interface MinSupportedStack {

    void push(int x);

    Integer pop() ;

    // Get the minimum number in the entire stack
    Integer getMinimum();

}
