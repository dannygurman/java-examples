package springexamples.aop.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springexamples.aop.config.AppConfiguration;
import springexamples.aop.model.Employee;
import springexamples.aop.service.EmployeeService;

public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(AppConfiguration.class);
		EmployeeService employeeService = context.getBean("employeeService", EmployeeService.class);
	    Employee em1 = context.getBean("em1", Employee.class);
		employeeService.setEmployee(em1);

		System.out.println(employeeService.getEmployee().getName());
		
		employeeService.getEmployee().setName("Myname");
		
		// Uncomment to see exception - employeeService.getEmployee().throwException();

		context.close();
	}

}
