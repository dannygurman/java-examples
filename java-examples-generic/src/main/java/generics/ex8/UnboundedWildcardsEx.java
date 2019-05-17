package generics.ex8;

import java.util.Arrays;
import java.util.List;

public class UnboundedWildcardsEx {

	public static void printListWildcard(List<?> list) {
		for (Object elem: list)
			System.out.print(elem + " ");
				System.out.println();
	}

	public static void printListObject(List<Object> list) {
		for (Object elem : list)
			System.out.println(elem + " ");
				System.out.println();
	}


	public static void main(String[] args) {
		List<Integer> li = Arrays.asList(1, 2, 3);
		List<String>  ls = Arrays.asList("one", "two", "three");
		printListWildcard(li);
		printListWildcard(ls);

		//printListObject(li);//Compilation error-List<Integer> NOT subtypes of List<Object>!
		//printListObject(ls);
	}

}
