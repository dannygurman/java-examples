package dependancyInjection.serviceLocatorSegregated;

import dependancyInjection.MovieFinder;


public class ServiceLocator implements MovieFinderLocator {

	private static ServiceLocator soleInstance;
	private MovieFinder movieFinder;

	public ServiceLocator(MovieFinder movieFinder) {
		this.movieFinder = movieFinder;
	}

	public static void load(ServiceLocator arg) {
		soleInstance = arg;
	}	    

	public static ServiceLocator locator() {
        return soleInstance;
    }
    public MovieFinder movieFinder() {
        return movieFinder;
    }
    
	

}
