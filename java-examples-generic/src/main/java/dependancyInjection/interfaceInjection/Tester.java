package dependancyInjection.interfaceInjection;

import dependancyInjection.Movie;

public class Tester {

	private Container container;

	public void test() {
		configureContainer();
		MovieLister lister = (MovieLister)container.lookup("MovieLister");
		Movie[] movies = lister.moviesDirectedBy("Sergio Leone");
	}

	private void configureContainer() {
		container = new Container();
		registerComponents();
		registerInjectors();
		container.start();
	}

	private void registerComponents(){
		container.registerComponent("MovieLister", MovieLister.class);
		container.registerComponent("MovieFinder", ColonMovieFinder.class);
	}

	private void registerInjectors(){
		 container.registerInjector(InjectFinder.class, container.lookup("MovieFinder"));
		 container.registerInjector(InjectFinderFilename.class, new FinderFilenameInjector());
	}
}
