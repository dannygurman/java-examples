package generics.ex3;

/*In addition to limiting the types you can use to instantiate
 *  a generic type, bounded type parameters 
 *  allow you to invoke methods defined in the bounds:
 */
public class NaturalNumberTest {

	public static void main(String[] args) {
		NaturalNumber <Integer> naturalNumber=new NaturalNumber <Integer>(2);
		System.out.print("Is even:"+naturalNumber.isEven());
	}
}

