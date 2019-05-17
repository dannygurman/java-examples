package generics.ex7;

import java.util.Arrays;
import java.util.List;

/**
 * Upper Bounded Wildcards
 * @author dannyg
 *
 */
public class WildcardExample {

	//public static double sumOfList(List<? extends Number> list) {
	public static<T extends Number> double sumOfList(List<T> list) {
		double s = 0.0;
		for (Number n : list)
			s += n.doubleValue();
				return s;
	}

	public static double sumOfList2(List<? extends Number> list) {	
		double s = 0.0;
		for (Number n : list)
			s += n.doubleValue();
				return s;
	}

	public static void main(String[] args) {
		List<Integer> li = Arrays.asList(1, 2, 3);
		System.out.println("twosum = " + sumOfList(li));
		
		List<Double> ld = Arrays.asList(1.2, 2.3, 3.5);
		System.out.println("twosum = " + sumOfList(ld));
		
		List<Integer> li2 = Arrays.asList(1, 2, 3);
		System.out.println("twosum = " + sumOfList2(li2));
		
		List<Double> ld2 = Arrays.asList(1.2, 2.3, 3.5);
		System.out.println("twosum = " + sumOfList2(ld2));
		

	}

}
