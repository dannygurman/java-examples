package designPatterns.creational.builder.ex1;

public class Hotel {
	private String name;

	public Hotel() {}
	
	public Hotel(String n) {
		this.name = n;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
