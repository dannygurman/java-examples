package passingParameter.ex1;

import passingParameter.Point;


public class PassByValue1 {

	

	//-----------
	public static  void main(String [] args){
		PassByValue1 pbv=new PassByValue1();
		
		Point p1=new Point(1,2);	
		Point p2=new Point(3,4);	
		
		System.out.println("before");
		p1.print();
		p2.print();
		
		pbv.badSwap(p1, p2);
		
		System.out.println("after");
		p1.print();
		p2.print();
		
		
	}
//-------------------

	//---------------
	public void badSwap(Point var1, Point var2){
	Point temp = var1;
		var1 = var2;
		var2 = temp;
	}
//	-------------
	
}
