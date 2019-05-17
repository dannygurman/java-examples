package concurrency.locks;

/**
 * http://tutorials.jenkov.com/java-concurrency/locks.html
 * Notice the synchronized(this) block in the inc() method. 
 * This block makes sure that only one thread can execute the return ++count at a time. 
 * The code in the synchronized block could have been more advanced, 
 * but the simple ++count suffices to get the point across.
 */

public class Counter{

	  private int count = 0;

	  public int inc(){
	    synchronized(this){
	      return ++count;
	    }
	  }
	}