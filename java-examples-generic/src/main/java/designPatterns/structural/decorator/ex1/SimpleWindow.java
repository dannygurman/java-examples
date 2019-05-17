package designPatterns.structural.decorator.ex1;

public class SimpleWindow implements Window {
	public void draw() {
		System.out.println("Draw in SimpleWindow");
	}
	public String getDescription() {
		return "simple window";
	}


}
