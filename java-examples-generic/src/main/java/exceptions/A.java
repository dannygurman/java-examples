package exceptions;

public class A {

	public void x() throws Exception {
		throw new Exception("In A");
	}

	public void y() throws Exception {
		try {
			x();
		}catch (Exception e) {
			throw new Exception("In B" , e);
		}
	}

	
}
