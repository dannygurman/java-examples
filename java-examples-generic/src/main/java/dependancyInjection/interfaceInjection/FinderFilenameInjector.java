package dependancyInjection.interfaceInjection;

public class FinderFilenameInjector implements Injector{

	 public void inject(Object target)  {
	      ((InjectFinderFilename)target).injectFilename("movies1.txt");      
	    }
}
