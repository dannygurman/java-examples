package designPatterns.structural.composite.menuiterator;

import java.util.Iterator;
import java.util.ArrayList;

public class Menu extends MenuComponent { //The composite
 
	ArrayList menuComponents = new ArrayList();
	String name;
	String description;
  
	public Menu(String name, String description) {
		this.name = name;
		this.description = description;
	}
 
	public void add(MenuComponent menuComponent) {
		menuComponents.add(menuComponent);
	}
 
	public void remove(MenuComponent menuComponent) {
		menuComponents.remove(menuComponent);
	}
 
	public MenuComponent getChild(int i) {
		return (MenuComponent)menuComponents.get(i);
	}
 
	public String getName() {
		return name;
	}
 
	public String getDescription() {
		return description;
	}

  
	public Iterator createIterator() {
		return new CompositeIterator(menuComponents.iterator());
		//Notice - the external iterator is used.
		//Its constructor get the array list (internal) iterator.
	}
 
 
	public void print() {
		System.out.print("\n" + getName());
		System.out.println(", " + getDescription());
		System.out.println("---------------------");
  
		Iterator iterator = menuComponents.iterator(); 
		//Notice - this is the ArrayList iterator (internal iterator - not exposed outside)
		while (iterator.hasNext()) {
			MenuComponent menuComponent = (MenuComponent)iterator.next();
			menuComponent.print(); //Recursion
		}
	}
}
