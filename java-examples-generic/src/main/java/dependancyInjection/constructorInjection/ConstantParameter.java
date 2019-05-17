package dependancyInjection.constructorInjection;

public class ConstantParameter implements Parameter{

	String name;

	public ConstantParameter(String name) {
		this.name = name;
	}
}
