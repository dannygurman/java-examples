package cloning.ex4.case2;

class Test	{


	public static PointOne copyPoint(PointOne point) {	   
		if (point instanceof PointTwo) {
			PointTwo pointTwo = (PointTwo)point;
			return new PointTwo(pointTwo.getX(), pointTwo.getY(), pointTwo.getZ());
		}
		return new PointOne(point.getX(), point.getY());
	}  


	public static void main(String[] args)
	{
		PointOne one = new PointOne (1,2);
		PointTwo two = new PointTwo(1,2,3);

		PointOne clone1 = copyPoint(one);
		PointOne clone2 = copyPoint(two);

		//Let check for class types
		System.out.println("clone1.getClass():" + clone1.getClass());
		System.out.println("clone2.getClass():" + clone2.getClass());
	}

	//	    output
	//	   clone1.getClass():class cloning.ex4.case2.PointOne
	//     clone2.getClass():class cloning.ex4.case2.PointTwo - now its good
}
