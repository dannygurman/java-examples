package designPatterns.behavioral.Strategy.ex1;
/**
strategy pattern (also known as the policy pattern) 
is a particular software design pattern, whereby algorithms.algorithms
 can be selected at runtime.
 
The strategy pattern is intended to provide 
a means to define a family of algorithms.algorithms, encapsulate
each one as an object, and make them interchangeable. 

The strategy pattern lets the algorithms.algorithms vary
independently from clients that use them.
For instance, a class that performs validation 
on incoming data may use a strategy pattern to 
select a validation algorithm based on
the type of data, the source of the data, 
or other discriminating factors. These factors
are not known for each case until run-time, and 
may require radically different validation to be 
performed. The validation strategies, encapsulated 
separately from the validating object, may be used 
by other validating objects in different areas of 
the system 
**/
public class StrategyExample {

	public static void main(String[] args) {

		Context context;

		// Three contexts following different strategies
		context = new Context(new ConcreteStrategyAdd());
		int resultA = context.executeStrategy(3,4);
		System.out.println("resultA "+resultA);

		context = new Context(new ConcreteStrategySubtract());
		int resultB = context.executeStrategy(3,4);
		System.out.println("resultB "+resultB);

		context = new Context(new ConcreteStrategyMultiply());
		int resultC = context.executeStrategy(3,4);
		System.out.println("resultC "+resultC);
	}
}
