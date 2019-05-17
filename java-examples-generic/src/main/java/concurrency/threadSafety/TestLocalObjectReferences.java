package concurrency.threadSafety;

/**
 * Local references to objects are a bit different.
 * The reference itself is not shared. 
 * The object referenced however, is not stored in each threads's local stack.
 * All objects are stored in the shared heap. 
 * If an object created locally never escapes the method it was created in, it is thread safe. 
 * In fact you can also pass it on to other methods and objects as long as none of these methods or objects make the passed object available to other threads.
 * Here is an example of a thread safe local object:
 *    
 *    
 * The LocalObject instance in this example is not returned from the method, nor is it passed to any other objects that are accessible from outside the someMethod() method.
 * Each thread executing the someMethod() method will create its own LocalObject instance and assign it to the localObject reference.
 * Therefore the use of the LocalObject here is thread safe.
 * In fact, the whole method someMethod() is thread safe.
 * Even if the LocalObject instance is passed as parameter to other methods in the same class, or in other classes, the use of it is thread safe.
 *
 * The only exception is of course, if one of the methods called with the LocalObject as parameter,
 * stores the LocalObject instance in a way that allows access to it from other threads.
 */




public class TestLocalObjectReferences {

	public void someMethod(){

		LocalObject localObject = new LocalObject();

		localObject.callMethod();
		method2(localObject);
	}

	public void method2(LocalObject localObject){
		localObject.setValue("value");
	}

}
