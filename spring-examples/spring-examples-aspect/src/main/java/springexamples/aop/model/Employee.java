package springexamples.aop.model;

import org.springframework.stereotype.Component;
import springexamples.aop.aspect.Loggable;

@Component
public class Employee {

	public Employee() {
	}

	public Employee(String name) {
		this.name = name;
	}

	private String name;
	
	public String getName() {
		return name;
	}

	@Loggable
	public void setName(String nm) {
		this.name=nm;
	}
	
	public void throwException(){
		throw new RuntimeException("Dummy Exception");
	}
	
}
