package concurrency.doubleCheckedLocking;

/*
 * If the helper object is static (one per class loader), 
 * an alternative is the initialization on demand holder
 * 
 * This relies on the fact that inner classes are not loaded until they are referenced.
 */


class FooUseStatic {

	private static class HelperHolder {
		public static Helper helper = new Helper();
	}

	public static Helper getHelper() {
		return HelperHolder.helper;
	}
}