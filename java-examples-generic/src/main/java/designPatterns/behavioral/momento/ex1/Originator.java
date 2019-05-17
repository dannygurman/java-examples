package designPatterns.behavioral.momento.ex1;

public class Originator {

	private String currentState;
	
	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public Memento saveToMemento() {
		return new Memento(this.currentState);
	}
	
	public void loadFromMemento(Memento m) {
		this.currentState = m.loadState();
	}
}
