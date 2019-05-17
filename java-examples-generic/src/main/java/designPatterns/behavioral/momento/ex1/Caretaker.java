package designPatterns.behavioral.momento.ex1;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
	
	private static List<Memento> saves = new ArrayList<Memento>();
	
	public static void main(String[] args) {
		Originator o = new Originator();

		o.setCurrentState("State 1");
		System.out.println(o.getCurrentState());

		o.setCurrentState("State 2");
		System.out.println(o.getCurrentState());

		Memento newMemento = o.saveToMemento();
		saves.add(newMemento);

		o.setCurrentState("State 3");
		System.out.println(o.getCurrentState());

		saves.add(o.saveToMemento());

		o.setCurrentState("State 4");
		System.out.println(o.getCurrentState());
		
		//rollback to first 'save'
		Memento savedMemento = saves.get(0);
		o.loadFromMemento(savedMemento);
		System.out.println(o.getCurrentState());
	}
	
}
