package springexamples.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class EmployeeAroundAspect {

	@Around("execution(* springexamples.aop.model.Employee.getName())")
	public Object employeeAroundAdvice(ProceedingJoinPoint pjp){
        Class<?> targetClass = pjp.getTarget().getClass();
        String classname = targetClass.getCanonicalName();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String methodname = methodSignature.getMethod().getName();
		System.out.println("Before invoking getName() method");
		Object value = null;
		try {
			value = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("After invoking getName() method. Return value="+value);
		return value;
	}

}
