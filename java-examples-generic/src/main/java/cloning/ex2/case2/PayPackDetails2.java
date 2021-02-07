package cloning.ex2.case2;

import lombok.Data;

@Data
public class PayPackDetails2 implements Cloneable {
	private double basicSalary = 500000d;
	private double incentive = 50000d;

	public PayPackDetails2 clone() throws CloneNotSupportedException {
	return (PayPackDetails2)super.clone();	
	}
	
	public double getSalary() {
		return getBasicSalary()+getIncentive();
	}


}

