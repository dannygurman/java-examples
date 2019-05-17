package cloning.ex2.case5;

import cloning.ex2.case4.PayPackDetails4;


//Using copy constructor for PayPackDetails too instead of clone method.

//In fact  it resolves many problems with flawed clone method:
//
//1. None of the classes have to implement the marker interface Cloneable
//2. As clone is not needed, there is no need of catching CloneNotSupportedException
//3. As clone is not needed, there is no need of typecasting the object on calling super.clone()



public class Employee5 implements Cloneable {

	private String name;


	private String identifier;

	private final PayPackDetails4 packDetails;

	public Employee5(String name, String identifier, AdvancedPayPackDetails packDetails) {
		this.name = name;
		this.identifier = identifier;
		this.packDetails = packDetails;
	}

	protected Employee5(Employee5 emp) throws CloneNotSupportedException{
		name = emp.name;
		identifier = emp.identifier;
		packDetails =new PayPackDetails4 (emp.packDetails);//Note - using PayPackDetails3 copy constructor
	}

	@Override
	public Employee5 clone() throws CloneNotSupportedException {
		return new Employee5(this); //Call to copy constructor
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
		Employee5 employee1 = new Employee5("Ram","1",new AdvancedPayPackDetails());
		System.out.println("1:");
		employee1.print();

		Employee5 employee2 = employee1.clone();
		System.out.println("2:");
		employee2.print();

		//		Results:1:
		//			name:Ram  id:1  package:600000.0  
		//			2:
		//			name:Ram  id:1  package:550000.0  - NOT 600000 !
		
		//And the reason is obvious. 
		//The copy constructor of Employee did not know about this new class(AdvancedPayPackDetails) created.
		//We can actually modify the Employee constructor to include instanceOf checks for PayPackDetails, 
		//but this is not the right way of doing things.
		//Rather it is better if we revert back to our earlier solution where
		//we used copy constructor in case of final fields
		//and use clone method for the classes which have Inheritance hierarchy.


	}
}
