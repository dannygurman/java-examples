package dependancyInjection.serviceLocatorDynamic;

import dependancyInjection.Movie;
import dependancyInjection.serviceLocatorBasic.ColonMovieFinder;
import dependancyInjection.serviceLocatorBasic.MovieLister;

public class Tester {

	 public void testSimple() {
	        configure();
	        MovieLister lister = new MovieLister();
	        Movie[] movies = lister.moviesDirectedBy("Sergio Leone");
	       // assertEquals("Once Upon a Time in the West", movies[0].getTitle());
	    }
	 
	 private void configure() {
		 ServiceLocator locator = new ServiceLocator();
	        locator.loadService("MovieFinder", new ColonMovieFinder("movies1.txt"));
	        ServiceLocator.load(locator);
	    }
}
