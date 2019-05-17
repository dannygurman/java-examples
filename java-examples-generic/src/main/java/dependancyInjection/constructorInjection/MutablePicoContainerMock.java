package dependancyInjection.constructorInjection;

public interface MutablePicoContainerMock {

	public void registerComponentImplementation(Object componentKey, Class componentImplementatio, Parameter[] parameters);
	public void registerComponentImplementation(Class componentImplementation);
	public Object getComponentInstance(Object componentKey);
}
