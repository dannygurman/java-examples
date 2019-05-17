package dependancyInjection.constructorInjection;

import java.util.ArrayList;
import java.util.List;

import dependancyInjection.Movie;

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
