package cloning.ex3.case1;

public class TestCloning {
	 
    public static void main(String[] args) throws CloneNotSupportedException
    {
        Department dept = new Department(1, "Human Resource");
        Employee original = new Employee(1, "Admin", dept);
       
        //Lets create a clone of original object
        Employee cloned = (Employee) original.clone();
       
        //Let verify using employee id, if cloning actually workded
        System.out.println("Cloned id: "+cloned.getEmpoyeeId());
 
        //Verify JDK's rules
 
        //Must be true and objects must have different memory addresses
        System.out.println("original != cloned:" + (original != cloned) );
 
        //As we are returning same class; so it should be true
        System.out.println("original.getClass() == cloned.getClass() : "+( original.getClass() == cloned.getClass()));
 
        //Default equals method checks for references so it should be false. If we want to make it true,
        //we need to override equals method in Employee class.
        System.out.println("original.equals(cloned): " + (original.equals(cloned)));
        
        //Let change the department name in cloned object and we will verify in original object
        cloned.getDepartment().setName("Finance");
        System.out.println("");
        System.out.println("Original department name:" + original.getDepartment().getName());
    }
//    Output:
//    Cloned id: 1
//    original != cloned:true
//    original.getClass() == cloned.getClass() : true
//    original.equals(cloned): false
    
   // Original department name:Finance!
    //Oops, cloned object changes are visible in original also.
    //This way cloned objects can make havoc in system if allowed to do so.
    //Anybody can come and clone your application objects and do whatever he likes
}
