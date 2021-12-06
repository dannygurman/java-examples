package generics.ex9;

import java.util.ArrayList;
import java.util.List;

public class Test {

	
	public static void main(String[] args) {
	
		B b = new B();
		A a = b;
		//This example shows that inheritance of regular classes follows this rule of subtyping:
		// class B is a subtype of class A if B extends A. This rule does not apply to generic types:

		List<B> lb = new ArrayList<B>();
	//	List<A> la = lb;   // compile-time error
        List < ? extends A> lea = lb;

		// Integer extends Number
		List <Integer> listI =new ArrayList<Integer>();
		List<? extends Integer> listIE = listI;
		List<? extends Number>  listNE = listIE;  // OK. List<? extends Integer> is a subtype of List<? extends Number>
		List<? extends Number>  listNE2 = listI;
		
		//Lower Bounded Wildcards
		
		
		List <Number> listNum =new ArrayList<Number>();
		List <? super Number> listNS =listNum;
		List<? super Integer>  listIS = listNS;
		List<? super Integer>  listIS2 = listNum;
		
		
	}

}
