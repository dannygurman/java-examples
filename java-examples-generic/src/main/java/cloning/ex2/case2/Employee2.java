package cloning.ex2.case2;

import lombok.Data;

/** This example solve the issue describe in case1- see  below
 * BUT -
 * If PayPackDetails is composed with other object references, we have to override clone method for that object too 
 * and call its clone method inside PayPackDetails.
 * 
 * 
 * The composed object class should also implement Cloneable interface. 
 * As it is always the case, we also have to handle CloneNotSupportedException.
 * 
 * Also - If we have - private FINAL PayPackDetails packDetails we have a problem:
 * As the field is declared final, we can not assign a new value to it in clone method as it is declared final.
 *  The solution: Use a copy constructor and return the new instance from the clone (see case 3).

 * **/
@Data
public class Employee2 implements Cloneable {

	private String name;
	private String identifier;
	private PayPackDetails2 packDetails;

	public Employee2(String name, String identifier, PayPackDetails2 packDetails) {
		this.name = name;
		this.identifier = identifier;
		this.packDetails = packDetails;
	}


	@Override
	public Employee2 clone() throws CloneNotSupportedException {
		Employee2 employee = (Employee2)super.clone();
		employee.packDetails = packDetails.clone();
		return employee;

	}

	public void print() {
		StringBuffer objectDetails = new StringBuffer();
		objectDetails.append("name:").append(name).append("  ");
		objectDetails.append("id:").append(identifier).append("  ");;
		objectDetails.append("package:").append(packDetails.getSalary()).append("  ");;
		System.out.println(objectDetails);
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		Employee2 employee1 = new Employee2("Ram","1",new PayPackDetails2());
		System.out.println("1:");
		employee1.print();

		Employee2 employee2 = employee1.clone();
		System.out.println("2:");
		employee2.print();

		employee2.setName("Krish"); employee2.setIdentifier("2");		
		employee2.getPackDetails().setBasicSalary(700000d);

		System.out.println("3:");
		employee1.print();
		System.out.println("4:");
		employee2.print();


		//RESULT
//		1:
//		name:Ram  id:1  package:550000.0  
//		2:
//		name:Ram  id:1  package:550000.0  
//		3:
//		name:Ram  id:1  package:550000.0  (Now it is not changed - as desired)
//		4:
//		name:Krish  id:2  package:750000.0  


	}
}