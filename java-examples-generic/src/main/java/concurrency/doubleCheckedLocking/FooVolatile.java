package concurrency.doubleCheckedLocking;

//Works with acquire/release semantics for volatile
//Broken under Java 1.4 and earlier semantics for volatile
class FooVolatile {
	private volatile Helper helper;
	public Helper getHelper() {
		/*
		 * Note the usage of the local variable result which seems unnecessary.
 		For some versions of the Java VM, it will make the code 25% faster and for others, it won't hurt.
		 */
		Helper result = helper;
		if (result == null) {
			synchronized(this) {
				result = helper;
				if (result == null) {
					helper = result = new Helper();
				}
			}
		}
		return result;
	}

	// other functions and members...
}


/*
As of J2SE 5.0, thehe volatile keyword now ensures that multiple threads handle the singleton instance correctly.
*/