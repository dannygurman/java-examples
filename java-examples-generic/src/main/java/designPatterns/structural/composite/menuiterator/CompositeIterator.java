package designPatterns.structural.composite.menuiterator;

 
import java.util.*;
  
//The CompositeIterator is a SERIOUS iterator. It�s got the job of iterating 
//over the MenuItems in the component, and of making sure all the child 
//Menus (and child child Menus, and so on) are included.
//Using recursion ...

//We are implementing an external
//iterator so there is a lot more to keep track of. 
//For starters, an external iterator must maintain its 
//position in the iteration so that an outside client 
//can drive the iteration by calling hasNext() and 
//next(). But in this case, our code also needs to 
//maintain that position over a composite, recursive 
//structure. That�s why we use stacks to maintain 
//our position as we move up and down the 
//composite hierarchy.

public class CompositeIterator implements Iterator {
	Stack stack = new Stack();
   
	public CompositeIterator(Iterator iterator) {//Wrapping the Array list iterator
		stack.push(iterator);
	}
   
	public Object next() {
		if (hasNext()) {
			 /**
		     * stack.peek: Looks at the object at the top of this stack without removing it
		     * from the stack.*/
			Iterator iterator = (Iterator) stack.peek();
			MenuComponent component = (MenuComponent) iterator.next();
			if (component instanceof Menu) {//Menuitems does not have  iterator
				stack.push(component.createIterator());//Creating and pushing the CompositeIterator of the MenuItem
			} 
			return component;
		} else {
			return null;
		}
	}
  
	public boolean hasNext() {
		if (stack.empty()) {
			return false;
		} else {
			Iterator iterator = (Iterator) stack.peek();
			if (!iterator.hasNext()) {
				stack.pop();
				return hasNext();
			} else {
				return true;
			}
		}
	}
   
	public void remove() {
		throw new UnsupportedOperationException();
	}
}


