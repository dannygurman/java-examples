package cloning.ex3.case2;

//Our Department class has two attributes. id and name.
public class Department implements Cloneable {

    private int id;
    private String name;
 
    public Department(int id, String name)    {
        this.id = id;
        this.name = name;
    }
    
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
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