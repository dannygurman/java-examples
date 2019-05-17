package concurrency.doubleCheckedLocking;

/*
 * Semantics of final field in Java 5 can be employed to safely publish the helper object without using volatile.
 * The local variable wrapper is required for correctness. 
 * Performance of this implementation is not necessarily better than the volatile implementation.
 */
public class FooUsingFinal {
	private FinalWrapper<Helper> helperWrapper;

	public Helper getHelper() {
		FinalWrapper<Helper> wrapper = helperWrapper;
		if (wrapper == null) {
			synchronized(this) {
				if (helperWrapper == null) {
					helperWrapper = new FinalWrapper<Helper>(new Helper());
				}
				wrapper = helperWrapper;
			}
		}
		return wrapper.value;
	}
}