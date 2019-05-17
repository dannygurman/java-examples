package generics.ex1;

public class OrderPairUsage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OrderedPair<String, Integer> p1 = new OrderedPair<String, Integer>("Even", 8);

		OrderedPair<String, String>  p2 = new OrderedPair<String, String>("hello", "world");

		OrderedPair<String, Box<Integer>> p3 = new OrderedPair<String, Box<Integer>>("primes", new Box<Integer>(4));

		int intVal=p1.getValue();
		System.out.println(intVal);

		String  strVal=p2.getValue();
		System.out.println(strVal);

		intVal=p3.getValue().get();
		System.out.println(intVal);

	}

}
