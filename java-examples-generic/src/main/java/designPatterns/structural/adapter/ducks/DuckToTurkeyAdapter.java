package designPatterns.structural.adapter.ducks;
import java.util.Random;

public class DuckToTurkeyAdapter implements Turkey {
	Duck duck;
	Random rand;
 
	public DuckToTurkeyAdapter(Duck duck) {
		this.duck = duck;
		rand = new Random();
	}
    
	public void gobble() {
		duck.quack();
	}
  
	public void fly() {
		if (rand.nextInt(5)  == 0) {
		     duck.fly();
		}
	}
}
