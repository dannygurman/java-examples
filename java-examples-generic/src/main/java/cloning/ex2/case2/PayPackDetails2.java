package cloning.ex2.case2;


public class PayPackDetails2 implements Cloneable {
	private double basicSalary = 500000d;
	private double incentive = 50000d;

	public PayPackDetails2 clone() throws CloneNotSupportedException {
	return (PayPackDetails2)super.clone();	
	}
	
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

