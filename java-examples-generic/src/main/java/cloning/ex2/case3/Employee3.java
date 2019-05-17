package cloning.ex2.case3;

import cloning.ex2.case2.PayPackDetails2;

//Using copy constructor
//Note that the copy constructor access modifier is protected.

//See also case 4:Use copy constructor for PayPackDetails too instead of clone method.


public class Employee3 implements Cloneable {

	private String name;


	private String identifier;

	private final PayPackDetails2 packDetails;

	public Employee3(String name, String identifier, PayPackDetails2 packDetails) {
		this.name = name;
		this.identifier = identifier;
		this.packDetails = packDetails;
	}

	protected Employee3(Employee3 emp) throws CloneNotSupportedException{
		name = emp.name;
		identifier = emp.identifier;
		packDetails = emp.packDetails.clone();
	}

	@Override
	public Employee3 clone() throws CloneNotSupportedException {
		return new Employee3(this); //Call to copy constructor
	}

	public void print() {
		StringBuffer objectDetails = new StringBuffer();
		objectDetails.append("name:").append(name).append("  ");
		objectDetails.append("id:").append(identifier).append("  ");;
		objectDetails.append("package:").append(packDetails.getSalary()).append("  ");;
		System.out.println(objectDetails);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public void setBasicSalary(double basicSalary) {
		packDetails.setBasicSalary(basicSalary);
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		Employee3 employee1 = new Employee3("Ram","1",new PayPackDetails2());
		System.out.println("1:");
		employee1.print();

		Employee3 employee2 = employee1.clone();
		System.out.println("2:");
		employee2.print();

		employee2.setName("Krish"); employee2.setIdentifier("2");		
		employee2.setBasicSalary(700000d);

		System.out.println("3:");
		employee1.print();
		System.out.println("4:");
		employee2.print();
		
//		 Results: 1:
//			name:Ram  id:1  package:550000.0  
//			2:
//			name:Ram  id:1  package:550000.0  
//			3:
//			name:Ram  id:1  package:550000.0  
//			4:
//			name:Krish  id:2  package:750000.0
	}
}
