package designPatterns.structural.proxy.protectionproxy;
 
import java.lang.reflect.*;
 //InvocationHandler is part of reflection package

public class OwnerInvocationHandler implements InvocationHandler { 
	PersonBean person;//Reference to Real Subject
 
	public OwnerInvocationHandler(PersonBean person) {//Passing reference to Real Subject
		this.person = person;
	}
 
	/*Here�s the invoke method that gets called every time a method is invoked 	on the proxy.*/
	
	public Object invoke(Object proxy, Method method, Object[] args) 
			throws IllegalAccessException {
  
		try {
			//If the method is a getter, we go ahead  and invoke it on the real subject.
			if (method.getName().startsWith("get")) {
				return method.invoke(person, args);
				
				//Otherwise, if it is the setHotOrNotRating()  method we disallow 
				//it by throwing a IllegalAccessException.
   			} else if (method.getName().equals("setHotOrNotRating")) {
				throw new IllegalAccessException();
		//	Because we are the owner any other set method 
			//	is fine and we go ahead and invoke 	it on the real subject
			} else if (method.getName().startsWith("set")) {
				return method.invoke(person, args);
			} 
        } catch (InvocationTargetException e) {
        	//This will happen if the real subject throws an exception
            e.printStackTrace();
        } 
		//If any other method is called	we�re just going to return null rather than take a chance
		return null;
	}
}
