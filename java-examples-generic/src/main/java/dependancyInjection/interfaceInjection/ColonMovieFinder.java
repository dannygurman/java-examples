package dependancyInjection.interfaceInjection;

import java.util.ArrayList;
import java.util.List;

import dependancyInjection.Movie;

class ColonMovieFinder implements MovieFinder, InjectFinderFilename,Injector {
	String filename;

	public void injectFilename(String filename) {
		this.filename = filename;
	}

	public void inject(Object target) {
	    ((InjectFinder) target).injectFinder(this);        
	  }
	
	/** Mock*/
	public  List <Movie> findAll() {
		return new ArrayList <Movie>();
	}
}