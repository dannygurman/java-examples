package interview.fibunacci;
//Fibonacci Series using Recursion
public class FibunacciRecur {
    static int fib(int n)    {
        if (n <= 1)
            return n;
        int result =  fib(n-1) + fib(n-2);

        return result;
    }

    public static void main (String args[])
    {
        int n = 9;
        System.out.println(fib(n));
    }
}