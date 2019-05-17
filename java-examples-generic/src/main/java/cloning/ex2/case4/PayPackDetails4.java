package cloning.ex2.case4;

public class PayPackDetails4 {

	private double basicSalary = 500000d;
	private double incentive = 50000d;

	public PayPackDetails4() {	
	}

	public PayPackDetails4(PayPackDetails4 details){
		basicSalary = details.getBasicSalary();
		incentive = details.getIncentive();
	}



	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public double getIncentive() {
		return incentive;
	}

	public void setIncentive(double incentive) {
		this.incentive = incentive;
	}

	public double getSalary() {	
		return getBasicSalary()+getIncentive();	
	}


}