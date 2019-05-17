package cloning.ex2.case1;


public class PayPackDetails1{
	private double basicSalary = 500000d;
	private double incentive = 50000d;

	public double getSalary() {
		return getBasicSalary()+getIncentive();
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public double getIncentive() {
		return incentive;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public void setIncentive(double incentive) {
		this.incentive = incentive;
	}
}

