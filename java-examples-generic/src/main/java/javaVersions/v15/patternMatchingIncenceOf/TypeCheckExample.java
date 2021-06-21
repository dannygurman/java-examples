package javaVersions.v15.patternMatchingIncenceOf;

import java.util.Date;

public class TypeCheckExample {

    private static Date typeCheckExample_BeforePaternMatching(Person person) {
        if (person instanceof Employee) {
            Employee employee = (Employee) person;//Required before type check with pattern matching
            Date hireDate = employee.getHireDate();
            return hireDate;
        } else {
            return null;
        }
    }

    private static Date typeCheckExample_WithPaternMatching_V1(Person person) {
        //the JVM automatically casts the variable for us and assigns the result to the new binding variable.
        if (person instanceof Employee employee) {
            Date hireDate = employee.getHireDate();
            return hireDate;
        } else {
            return null;
        }
    }

    private static Date typeCheckExample_WithPaternMatching_V2(Person person) {
        //We can also combine the new binding variable with conditional statements:
        if (person instanceof Employee employee && employee.getYearsOfService() > 5) {
            Date hireDate = employee.getHireDate();
            return hireDate;
        } else {
            return null;
        }
    }

}
