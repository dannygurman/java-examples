package passingParameter.ex2;

import passingParameter.Point;

public class PassByValue2 {

	//-----------
	public static  void main(String [] args){
		PassByValue2 pbv=new PassByValue2();
		
		Point p1=new Point(0,0);	
		Point p2=new Point(0,0);	
		
		System.out.println("before");
		p1.print();
		p2.print();
		
		pbv.tricky(p1, p2);
		
		System.out.println("after");
		p1.print();
		p2.print();
		
		
	}
//-------------------

	//-------------------
	/**The method successfully alters the value of pnt1, 
	 * 	 even though it is passed by value; 
	 * however, a swap of pnt1 and pnt2 fails! 
	 * This is the major source of confusion. 
	 * In the main() method, pnt1 and pnt2 are nothing more than object references.
	 *  When you pass pnt1 and pnt2 to the tricky() method, Java passes the references 
	 *  by value just like any other parameter. This means the references passed to the 
	 *  method are actually copies of the original references
	 *  
	 *  Java does manipulate objects by reference, 
	 *  and all object variables are references. 
	 *  However, Java doesn't pass method arguments
	 *   by reference; it passes them by value. 

*/
	public void tricky(Point arg1, Point arg2)
	{
	  arg1.x = 100;
	  arg1.y = 100;
	  Point temp = arg1;
	  arg1 = arg2;
	  arg2 = temp;
	}
	//-------------------

}
