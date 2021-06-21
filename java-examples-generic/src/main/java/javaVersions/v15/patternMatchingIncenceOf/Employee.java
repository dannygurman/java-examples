package javaVersions.v15.patternMatchingIncenceOf;

import lombok.Data;

import java.util.Date;

@Data
public class Employee extends Person {
    Date hireDate;
    Integer yearsOfService;
}
