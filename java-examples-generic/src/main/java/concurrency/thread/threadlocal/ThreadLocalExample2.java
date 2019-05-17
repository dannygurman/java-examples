package concurrency.thread.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

/**Each thread holds an implicit reference to its copy of a thread-local
 * variable as long as the thread is alive and the <tt>ThreadLocal</tt>
 * instance is accessible; after a thread goes away, all of its copies of
 * thread-local instances are subject to garbage collection (unless other
 * references to these copies exist).
 * */

public class ThreadLocalExample2 {


	// Atomic integer containing the next thread ID to be assigned
	private static final AtomicInteger nextId = new AtomicInteger(0);

	// Thread local variable containing each thread's ID
	private static final ThreadLocal<Integer> threadId =new ThreadLocal<Integer>() {
		//@Override
		protected Integer initialValue() {
			return nextId.getAndIncrement();
		}
	};

	// Returns the current thread's unique ID, assigning it if necessary
	public static int get() {
		return threadId.get();
	}

}
