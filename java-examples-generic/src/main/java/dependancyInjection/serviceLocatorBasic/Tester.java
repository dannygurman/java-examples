package dependancyInjection.serviceLocatorBasic;

import dependancyInjection.Movie;

public class Tester {

	 public void testSimple() {
	        configure();
	        MovieLister lister = new MovieLister();
	        Movie[] movies = lister.moviesDirectedBy("Sergio Leone");
	       // assertEquals("Once Upon a Time in the West", movies[0].getTitle());
	    }
	 
	 private void configure() {
	        ServiceLocator.load(new ServiceLocator(new ColonMovieFinder("movies1.txt")));
	    }
}
