package dependancyInjection.serviceLocatorBasic;

import java.util.Iterator;
import java.util.List;

import dependancyInjection.Movie;
import dependancyInjection.MovieFinder;

/**
 * http://martinfowler.com/articles/injection.html
 * */
public class MovieLister {

	  private MovieFinder finder = ServiceLocator.movieFinder();
	  
	  public Movie[] moviesDirectedBy(String arg) {
	        List <Movie>allMovies = finder.findAll();
	        for (Iterator <Movie> it = allMovies.iterator(); it.hasNext();) {
	            Movie movie = (Movie) it.next();
	            if (!movie.getDirector().equals(arg)) it.remove();
	        }
	        return (Movie[]) allMovies.toArray(new Movie[allMovies.size()]);
	    }
}
