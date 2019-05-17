package designPatterns.behavioral.momento.ex1;

public class Memento {
	
	private String state;
	
	public Memento(String state) {
		this.state = state;
	}
	
	public void storeState(String state) {
		this.state = state;
	}
	
	public String loadState() {
		return state;
	}

}
