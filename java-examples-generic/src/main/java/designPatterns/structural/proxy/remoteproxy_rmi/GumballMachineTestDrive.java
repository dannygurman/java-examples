package designPatterns.structural.proxy.remoteproxy_rmi;
import java.rmi.*;

public class GumballMachineTestDrive {
 
	public static void main(String[] args) {
		GumballMachineRemote gumballMachine = null;
		int count;

		if (args.length < 2) {
			System.out.println("GumballMachine <name> <inventory>");
 			System.exit(1);
		}

		try {
			count = Integer.parseInt(args[1]);

//			Give the  service a name (that clients can use to look it up in the registry) 
//			and register it with the RMI registry.
//			When you bind the	service object, RMI swaps the service for the 
//			stub and puts the stub in the registery
					
			gumballMachine = new GumballMachine(args[0], count);
			Naming.rebind("//" + args[0] + "/gumballmachine", gumballMachine);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
