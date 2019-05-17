package designPatterns.structural.proxy.remoteproxy_rmi;

import java.rmi.*;
import java.rmi.server.*;
 
//Your service has to implement the remote interface�the one with the methods your client is going to call.

/*In order to work as a remote service object, your object needs some functionality 
related to �being remote�. 
The simplest way is to extend UnicastRemoteObject (from the java.rmi.server package) 
and let that class (your superclass) do the work for you.*/



public class GumballMachine	 extends UnicastRemoteObject implements GumballMachineRemote 
{
	State soldOutState;
	State noQuarterState;
	State hasQuarterState;
	State soldState;
	State winnerState;
 
	State state = soldOutState;
	int count = 0;
 	String location;

	public GumballMachine(String location, int numberGumballs) throws RemoteException {
	//	soldOutState = new SoldOutState(this);
	//	noQuarterState = new NoQuarterState(this);
		hasQuarterState = new HasQuarterState(this);
		soldState = new SoldState(this);
	//	winnerState = new WinnerState(this);

		this.count = numberGumballs;
 		if (numberGumballs > 0) {
			state = noQuarterState;
		} 
		this.location = location;
	}
 
 
	public void insertQuarter() {
		state.insertQuarter();
	}
 
	public void ejectQuarter() {
		state.ejectQuarter();
	}
 
	public void turnCrank() {
		state.turnCrank();
		state.dispense();
	}

	void setState(State state) {
		this.state = state;
	}
 
	void releaseBall() {
		System.out.println("A gumball comes rolling out the slot...");
		if (count != 0) {
			count = count - 1;
		}
	}

	public void refill(int count) {
		this.count = count;
		state = noQuarterState;
	}
 
	public int getCount() {
		return count;
	}
 
    public State getState() {
        return state;
    }
 
    public String getLocation() {
        return location;
    }
  
    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getWinnerState() {
        return winnerState;
    }
 
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("\nMighty Gumball, Inc.");
		result.append("\nJava-enabled Standing Gumball Model #2004");
		result.append("\nInventory: " + count + " gumball");
		if (count != 1) {
			result.append("s");
		}
		result.append("\n");
		result.append("Machine is " + state + "\n");
		return result.toString();
	}
}
