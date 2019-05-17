package cloning.ex2.case4;


//Using copy constructor for PayPackDetails too instead of clone method.

//In fact  it resolves many problems with flawed clone method:
//
//1. None of the classes have to implement the marker interface Cloneable
//2. As clone is not needed, there is no need of catching CloneNotSupportedException
//3. As clone is not needed, there is no need of typecasting the object on calling super.clone()

//But here comes the problem: Say suppose you have a subclass for PayPackDetails. Case 5:

public class Employee4 implements Cloneable {

	private String name;


	private String identifier;

	private final PayPackDetails4 packDetails;

	public Employee4(String name, String identifier, PayPackDetails4 packDetails) {
		this.name = name;
		this.identifier = identifier;
		this.packDetails = packDetails;
	}

	protected Employee4(Employee4 emp) throws CloneNotSupportedException{
		name = emp.name;
		identifier = emp.identifier;
		packDetails =new PayPackDetails4 (emp.packDetails);//Note - using PayPackDetails3 copy constructor
	}

	@Override
	public Employee4 clone() throws CloneNotSupportedException {
		return new Employee4(this); //Call to copy constructor
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
		Employee4 employee1 = new Employee4("Ram","1",new PayPackDetails4());
		System.out.println("1:");
		employee1.print();

		Employee4 employee2 = employee1.clone();
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
