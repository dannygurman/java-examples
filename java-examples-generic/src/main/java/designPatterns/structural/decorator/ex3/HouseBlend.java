package designPatterns.structural.decorator.ex3;

public class HouseBlend extends Beverage {
	public HouseBlend() {
		description = "House Blend Coffee";
	}
 
	public double cost() {
		return .89;
	}
}

