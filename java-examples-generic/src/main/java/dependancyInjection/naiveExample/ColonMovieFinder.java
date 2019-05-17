package dependancyInjection.naiveExample;

import java.util.ArrayList;
import java.util.List;

import dependancyInjection.Movie;
import dependancyInjection.MovieFinder;

public class ColonMovieFinder implements MovieFinder{

	String filename;
	
	public ColonMovieFinder(String filename) {
		this.filename = filename;
	}

	/** Mock*/
public  List <Movie> findAll() {
	return new ArrayList <Movie>();
}
	
}
