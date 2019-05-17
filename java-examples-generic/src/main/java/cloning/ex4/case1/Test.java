package cloning.ex4.case1;

   class Test	{
	    public static void main(String[] args)
	    {
	        PointOne one = new PointOne (1,2);
	        PointTwo two = new PointTwo(1,2,3);
	 
	        PointOne clone1 = new PointOne(one);
	        PointOne clone2 = new PointOne(two);
	 
	        //Let check for class types
	        System.out.println("clone1.getClass():" + clone1.getClass());
	        System.out.println("clone2.getClass():" + clone2.getClass());
	    }
	    
//	    output
//	    clone1.getClass():class cloning.ex4.case1.PointOne
//	    clone2.getClass():class cloning.ex4.case1.PointOne !!!!
//	    The problem with inheritance is that exact behavior is identified only in run time. 
//	    So, in our case if some class passed the instance of PointTwo in constructor of PointOne.
//	    In this case, you will get the instance of PointOne in 
//	    return where you passed instance of PointTwo as argument.
	}
