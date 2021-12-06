package generics.ex5;

import java.util.ArrayList;

public class ComareUtil {

	public static <T extends Comparable<T>> int countGreaterThan(ArrayList <T> aList, T elem) {
	    int count = 0;
	    for (T e : aList)
	        if (e.compareTo(elem) > 0)
	            ++count;
	    return count;
	}

	public static void main(String[] args) {
		ArrayList <IntegerCompare> intCompareListy=new  ArrayList<IntegerCompare>();
		intCompareListy.add(new IntegerCompare (1));
		intCompareListy.add(new IntegerCompare (4));
		intCompareListy.add(new IntegerCompare (11));
		
		IntegerCompare elem = new IntegerCompare(3);
		
		int count=ComareUtil.<IntegerCompare>countGreaterThan (intCompareListy, elem );
		System.out.print("Count:"+count);
	}
}
