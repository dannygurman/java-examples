package cloning.ex2.case5;

import cloning.ex2.case4.PayPackDetails4;

public class AdvancedPayPackDetails extends PayPackDetails4 {
	private double flexiblePayPercent = 10d;

	public AdvancedPayPackDetails() {		
	}

	public AdvancedPayPackDetails(AdvancedPayPackDetails details) {
		super(details);
		flexiblePayPercent = details.getFlexiblePayPercentage();
	}

	@Override
	public double getSalary() {
		return super.getSalary()+(getBasicSalary()*getFlexiblePayPercentage()/100);
	}

	public double getFlexiblePayPercentage() {
		return flexiblePayPercent;
	}

	public void setFlexiblePayPercent(double flexiblePayPercent) {
		this.flexiblePayPercent = flexiblePayPercent;
	}

}