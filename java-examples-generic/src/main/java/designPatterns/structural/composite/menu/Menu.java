package designPatterns.structural.composite.menu;

import java.util.Iterator;
import java.util.ArrayList;

public class Menu extends MenuComponent { //This is the composite
	ArrayList<MenuComponent> menuComponents = new ArrayList();
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
		return menuComponents.get(i);
	}
 
	public String getName() {
		return name;
	}
 
	public String getDescription() {
		return description;
	}
 
	public void print() {
		System.out.print("\n" + getName());
		System.out.println(", " + getDescription());
		System.out.println("---------------------");
  
		Iterator iterator = menuComponents.iterator(); //Internal iterator
		while (iterator.hasNext()) {
			MenuComponent menuComponent =  (MenuComponent)iterator.next();
			menuComponent.print();//Recursion - Menu extend MenuComponent
		}
	}
}
