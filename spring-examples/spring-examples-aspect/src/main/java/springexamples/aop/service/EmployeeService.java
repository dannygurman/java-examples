package springexamples.aop.service;

import org.springframework.stereotype.Service;
import springexamples.aop.model.Employee;

@Service
public class EmployeeService {

	private Employee employee;
	
	public Employee getEmployee(){
		return this.employee;
	}
	
	public void setEmployee(Employee e){
		this.employee=e;
	}
}
