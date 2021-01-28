package cloning.ex2.case1;

import lombok.Data;

/** this example demonstrate the problem of shallow cloning - see remarks below **/

@Data
public class Employee1 implements Cloneable {

	private String name;
	private String identifier;
	private PayPackDetails1 packDetails;

	public Employee1(String name, String identifier, PayPackDetails1 packDetails) {
		this.name = name;
		this.identifier = identifier;
		this.packDetails = packDetails;
	}

	@Override
	public Employee1 clone() throws CloneNotSupportedException {
		return (Employee1)super.clone();
	}

	public void print() {
		StringBuffer objectDetails = new StringBuffer();
		objectDetails.append("name:").append(name).append("  ");
		objectDetails.append("id:").append(identifier).append("  ");;
		objectDetails.append("package:").append(packDetails.getSalary()).append("  ");;
		System.out.println(objectDetails);
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		Employee1 employee1 = new Employee1("Ram","1",new PayPackDetails1());
		System.out.println("1:");
		employee1.print();

		Employee1 employee2 = employee1.clone();
		System.out.println("2:");
		employee2.print();

		employee2.setName("Krish");
		employee2.setIdentifier("2");
		employee2.getPackDetails().setBasicSalary(700000d);

		System.out.println("3:");
		employee1.print();
		System.out.println("4:");
		employee2.print();


		//RESULT
		//		    1:
		//			name:Ram  id:1  package:550000.0  
		//			2:
		//			name:Ram  id:1  package:550000.0
		
		
		//			3:
		//			name:Ram  id:1  package:750000.0  (!!!!!!!!!! - also changed)
		//			4:
		//			name:Krish  id:2  package:750000.0  
		
		//Now what do you think would the salary of employee1 be?
		//As we have increased the salary of cloned employee, we naturally expect the salary to be increased for him.
		//But the unexpected turn here is the salary for employee1 also gets increased.
		
		//Please note that when we clone an object, the constructor does NOT get called.
		//It would rather make a field-by-field copy of all the member variables present in the address location of the original object.
		//And now when there are object references, the reference gets copied but not the original object.
		
		//Hence both the original and cloned objects point to the same member object.
		//So changes made in one object would automatically be visible to the other. 
		

	}
}