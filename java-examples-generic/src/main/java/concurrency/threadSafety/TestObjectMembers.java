package concurrency.threadSafety;

public class TestObjectMembers {

	/**
	 * Object members are stored on the heap along with the object.
	 * Therefore, if two threads call a method on the same object instance and this method
	 * updates object members, the method is not thread safe.
	 */

	public static void main(String[] args) {

		NotThreadSafe sharedInstance = new NotThreadSafe();

		/**
		 * If two threads call the add() method simultanously on the same NotThreadSafe instance then it leads to race conditions.
		 * For instance:
		 * Notice how the two MyRunnable instances share the same NotThreadSafe instance.
		 * Therefore, when they call the add() method on the NotThreadSafe instance it leads to race condition.		
		 */
		new Thread(new MyRunnable(sharedInstance)).start();
		new Thread(new MyRunnable(sharedInstance)).start();

		/**
		 * However, if two threads call the add() method simultanously on different instances then it does not lead to race condition
		 * Now the two threads have each their own instance of NotThreadSafe so their calls to the add method doesn't interfere with each other.
		 * The code does not have race condition anymore.
		 * So, even if an object is not thread safe it can still be used in a way that doesn't lead to race condition.
		 */
		new Thread(new MyRunnable(new NotThreadSafe())).start();
		new Thread(new MyRunnable(new NotThreadSafe())).start();
	}
}
