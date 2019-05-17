package designPatterns.structural.composite.menuiterator;

import java.util.Iterator;
  
public class Waitress {
	MenuComponent allMenus;
 
	public Waitress(MenuComponent allMenus) {
		this.allMenus = allMenus;
	}
 
	public void printMenu() {
		allMenus.print();
	}
  
	public void printVegetarianMenu() {
		Iterator iterator = allMenus.createIterator(); //Using the "external" iterator

		System.out.println("\nVEGETARIAN MENU\n----");
		while (iterator.hasNext()) {
			MenuComponent menuComponent =  (MenuComponent)iterator.next();
			try {
				if (menuComponent.isVegetarian()) {
					menuComponent.print();
				}
			} catch (UnsupportedOperationException e) {}
//			We implemented isVegetarian() on the 
//			Menus to always throw an exception. If 
//			that happens we catch the exception, but 
//			continue with our iteration.
		}
	}
}
