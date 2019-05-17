package designPatterns.behavioral.chainOfResponsibility.ex1;

public class D200CoinHandler implements Handler {

	private Handler successor = new D500CoinHandler();
	
	public boolean handle(Coin c) {
		if(c instanceof D200Coin ) {
			System.out.println("Handling D200");
			return true;
		} else {
			System.out.println("Passing to successor");
			if(successor.handle(c)) {
				return true;
			}
		}
		System.out.println("Cannot handle : "+c);
		return false;
	}

}
