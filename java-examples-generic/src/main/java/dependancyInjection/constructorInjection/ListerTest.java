package dependancyInjection.constructorInjection;

import dependancyInjection.Movie;

public class ListerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MutablePicoContainerMock pico = configureContainer();
        MovieLister lister = (MovieLister) pico.getComponentInstance(MovieLister.class);
        Movie[] movies = lister.moviesDirectedBy("Sergio Leone");       
	}

	 private static MutablePicoContainerMock configureContainer() {
		 MutablePicoContainerMock pico = new DefaultPicoContainerMock();
	        Parameter[] finderParams =  {new ConstantParameter("movies1.txt")};
	        pico.registerComponentImplementation(MovieFinder.class, ColonMovieFinder.class, finderParams);
	        pico.registerComponentImplementation(MovieLister.class);
	        return pico;
	    }
	 
}
