package generics.ex6;

import java.util.ArrayList;

public class  Box<T> {

	private ArrayList<T>  list =new ArrayList<T>();        

	public void add(T t) {
		list.add(t);
	}

}