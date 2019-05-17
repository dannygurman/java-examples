package generics.ex2;

public class TestUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pair<Integer, String> p1 = new Pair<Integer, String>(1, "apple");
		Pair<Integer, String> p2 = new Pair<Integer, String>(2, "pear");
		boolean same = Util.<Integer, String>compare(p1, p2);
		System.out.print(same);

	}

}
