package designPatterns.structural.proxy.remoteproxy_rmi;

import java.rmi.*;
 
/* Extending Remote -Remote is a �marker� interface, which means it has no methods.
 * It has special  meaning for RMI, though, so you must follow this rule
 * 
 * its tell us that the interface is going to be used to support remote calls.


/*The remote interface defines the methods that 
a client can call remotely. 
It�s what the client will use as the class type for your service. 
Both the Stub and actual service will implement this!*/


/* Remote exception:
The remote interface is the one the client uses as the type for the service. 
In other words, the client invokes methods on something that implements the 
remote interface. 
That something is the stub, of course, and since the stub is 
doing networking and I/O, all kinds of Bad Things can happen. 
The client has to acknowledge the risks by handling or declaring the remote exceptions.
If the methods in an interface declare exceptions, any code calling methods on a 
reference of that type (the interface type) must handle or declare the exceptions.*/

/*Arguments and return values are primitives or Serializable
Arguments and return values of a remote method must be either primitive 
or Serializable. Think about it. 
Any argument to a remote method has to be packaged up and shipped across the network, and that�s done through 
Serialization. 
Same thing with return values. 
If you use primitives, Strings, and the majority of types in the API (including arrays and collections),
you�ll be fine. 
If you are passing around your own types, just be sure that you make your classes 
implement Serializable.*/



public interface GumballMachineRemote extends Remote {
	public int getCount() throws RemoteException;
	public String getLocation() throws RemoteException;
	public State getState() throws RemoteException;
}
