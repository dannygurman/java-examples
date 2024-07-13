package interview.fibunacci;

// Java program for Fibonacci Series using Space // Optimized Method
public class FibuOptimized {
    static int fib(int n)    {
        int a = 0, b = 1, sum;
        if (n == 0) {
            return a;
        }
        for (int i = 2; i <= n; i++)
        {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static void main (String args[])
    {
        int n = 9;
        System.out.println(fib(n));
    }
}
