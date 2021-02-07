package cloning.ex2.case4;

import lombok.Data;

@Data
public class PayPackDetails4 {

	private double basicSalary = 500000d;
	private double incentive = 50000d;

	public PayPackDetails4() {	
	}

	public PayPackDetails4(PayPackDetails4 details){
		basicSalary = details.getBasicSalary();
		incentive = details.getIncentive();
	}


	public double getSalary() {	
		return getBasicSalary()+getIncentive();	
	}


}