package designPatterns.structural.proxy.protectionproxy;

import java.lang.reflect.*;
import java.util.*;

public class MatchMakingTestDrive {
	Hashtable datingDB = new Hashtable();
 	
	public static void main(String[] args) {
		MatchMakingTestDrive test = new MatchMakingTestDrive();
		test.drive();
	}
 
	public MatchMakingTestDrive() {
		initializeDatabase();
	}

	public void drive() {
		PersonBean joe = getPersonFromDatabase("Joe Javabean"); 
		PersonBean ownerProxy = getOwnerProxy(joe);
		System.out.println("Name is " + ownerProxy.getName());
		ownerProxy.setInterests("bowling, Go");
		System.out.println("Interests set from owner proxy");
		try {
			ownerProxy.setHotOrNotRating(10);
		} catch (Exception e) {
			System.out.println("Can't set rating from owner proxy");
		}
		System.out.println("Rating is " + ownerProxy.getHotOrNotRating());

		PersonBean nonOwnerProxy = getNonOwnerProxy(joe);
		System.out.println("Name is " + nonOwnerProxy.getName());
		try {
			nonOwnerProxy.setInterests("bowling, Go");
		} catch (Exception e) {
			System.out.println("Can't set interests from non owner proxy");
		}
		nonOwnerProxy.setHotOrNotRating(3);
		System.out.println("Rating set from non owner proxy");
		System.out.println("Rating is " + nonOwnerProxy.getHotOrNotRating());
	}

	
	
	
//	This method takes a person object (the real  subject) 	//and returns a proxy for it. 
//	Because the  proxy has the same interface as the subject, we 	return a PersonBean.
//	This code creates the  proxy. 
//	Now this is some  mighty ugly code, so let�s step through it carefully.
	
	PersonBean getOwnerProxy(PersonBean person) {
		//To create a proxy we use the static newProxyInstance  method on the Proxy class...
		
        return (PersonBean) Proxy.newProxyInstance( 
        		//We pass it the classloader for our subject...
            	person.getClass().getClassLoader(),
            	//and the set of interfaces the proxy needs to implement...
            	person.getClass().getInterfaces(),
            	//and an invocation handler, in this  case our OwnerInvocationHandler.
            	//We pass the real subject into the constructor  of the invocation handler. 
            	//The handler gets access to the real subject.
                new OwnerInvocationHandler(person));
	}

	

	PersonBean getNonOwnerProxy(PersonBean person) {	
        return (PersonBean) Proxy.newProxyInstance(        	
            	person.getClass().getClassLoader(),            
            	person.getClass().getInterfaces(),            
                new NonOwnerInvocationHandler(person));
	}

	
	PersonBean getPersonFromDatabase(String name) {
		return (PersonBean)datingDB.get(name);
	}

	void initializeDatabase() {
		PersonBean joe = new PersonBeanImpl();
		joe.setName("Joe Javabean");
		joe.setInterests("cars, computers, music");
		joe.setHotOrNotRating(7);
		datingDB.put(joe.getName(), joe);

		PersonBean kelly = new PersonBeanImpl();
		kelly.setName("Kelly Klosure");
		kelly.setInterests("ebay, movies, music");
		kelly.setHotOrNotRating(6);
		datingDB.put(kelly.getName(), kelly);
	}
}
