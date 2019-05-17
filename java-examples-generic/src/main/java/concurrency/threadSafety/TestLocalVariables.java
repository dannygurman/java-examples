package concurrency.threadSafety;

/*http://tutorials.jenkov.com/java-concurrency/thread-safety.html
Local variables are stored in each thread's own stack.
 That means that local variables are never shared between threads.
  That also means that all local primitive variables are thread safe.
   Here is an example of a thread safe local primitive variable:
 */

public class TestLocalVariables {

	public void someMethod(){		  
		long threadSafeInt = 0;
		threadSafeInt++;
		System.out.print(threadSafeInt);
	}
}
