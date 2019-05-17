package designPatterns.structural.decorator.structure;

public abstract class Decorator implements Component{

	@Override
	public  abstract void methodA() ; //Requiring the decorators to implement
	

	@Override
	public void methodB() {
		// BLA - implementation
		
	}
	
	public void otherMethod() {
	
		
	}
	
}
