package concurrency.callable.ex1;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*

From Future JavaDoc:
A <tt>Future</tt> represents the result of an asynchronous	computation.
Methods are provided to check if the computation is	 complete,
to wait for its completion, and to retrieve the result of the computation.



From Callable JavaDoc:
A task that returns a result and may throw an exception.
Implementors define a single method with no arguments called :call.

The Callable interface is similar to Runnable , in that both are designed for classes whose
 instances are potentially executed by another thread.

A Runnable , however, does not return a result and cannot  throw a checked exception.
*/


public class CallableExample {


	public static class WordLengthCallable  implements Callable {
		private String word;

		public WordLengthCallable(String word) {
			this.word = word;
		}
		//call does not have parameters
		public Integer call() {
			return Integer.valueOf(word.length());
		}
	}

	public static void main(String args[]) throws Exception {
		String [] words ={ "hello","world","dog"};

		ExecutorService pool = Executors.newFixedThreadPool(3);

		Set<Future<Integer>> futureSet = new LinkedHashSet<Future<Integer> >();

		for (String word: words) {
			//Creating new callable for every word
			Callable<Integer> wordLengthCallable = new WordLengthCallable(word);

			//Send callable to executer thread pool which return the Future
			Future<Integer> future = pool.submit(wordLengthCallable);
			futureSet.add(future);
		}
		int sum = 0;
		for (Future<Integer> future : futureSet) {
			//*future.get() Waits (block) if necessary for the computation to complete, and then
			//* retrieves its result.
			int curLength = future.get(); //Getting the result
			System.out.printf("curLength is %s%n", curLength);
			sum += future.get();
		}
		System.out.printf("The twosum of lengths is %s%n", sum);

		System.exit(sum);
	}

}


