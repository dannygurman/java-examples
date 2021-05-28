package exceptions;

public class TestException {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		A a = new A();
		try {
			a.y();
		} catch (Exception e ) {
			System.out.println (e.getMessage());
            System.out.println ("---------");
			e.printStackTrace();
		}

	}

}
