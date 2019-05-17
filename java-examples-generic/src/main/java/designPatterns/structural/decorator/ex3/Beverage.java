package designPatterns.structural.decorator.ex3;

/** Beverage is the Component*/

public abstract class Beverage {
	String description = "Unknown Beverage";
  
	public String getDescription() {
		return description;
	}
 
	public abstract double cost();
}
