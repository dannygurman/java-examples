package cloning.ex3.case1;

//Our Department class has two attributes. id and name.
public class Department
{
    private int id;
    private String name;
 
    public Department(int id, String name)    {
        this.id = id;
        this.name = name;
    }
    //Accessor/mutators methods will go there

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}