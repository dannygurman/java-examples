package dependancyInjection.constructorInjection;

public class DefaultPicoContainerMock implements MutablePicoContainerMock{

	public void registerComponentImplementation(Object componentKey, Class componentImplementatio, Parameter[] parameters){};
	public void registerComponentImplementation(Class componentImplementation){};
	public Object getComponentInstance(Object componentKey){return null;};
}
