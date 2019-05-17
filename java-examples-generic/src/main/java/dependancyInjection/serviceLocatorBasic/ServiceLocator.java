package dependancyInjection.serviceLocatorBasic;

import dependancyInjection.MovieFinder;


public class ServiceLocator {

	 private static ServiceLocator soleInstance;
	 private MovieFinder movieFinder;
	    
	    
	    public ServiceLocator(MovieFinder movieFinder) {
	        this.movieFinder = movieFinder;
	    }
	    
	   public static void load(ServiceLocator arg) {
	        soleInstance = arg;
	    }	    
	    
	    public static MovieFinder movieFinder() {
	        return soleInstance.movieFinder;
	    }
	   
}
