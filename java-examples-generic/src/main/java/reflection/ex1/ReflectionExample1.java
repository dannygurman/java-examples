package reflection.ex1;


import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class ReflectionExample1 {

    private final static String FIRST_NAME_FIELD = "firstName";
    private final static String LAST_NAME_FIELD = "lastName";
    private final static String EMPLOYEE_ID_FIELD =  "employeeId";


    @Test
    public void testReflection() {

        List<Field> allFields = Arrays.asList(Person.class.getDeclaredFields());
       assertEquals(2, allFields.size());


        Field lastName = allFields.stream()
                .filter(field -> field.getName().equals(LAST_NAME_FIELD))
                .findFirst().orElseThrow(() -> new RuntimeException("Field not found"));
        assertEquals(String.class, lastName.getType());


        Field firstName = allFields.stream()
                .filter(field -> field.getName().equals(FIRST_NAME_FIELD))
                .findFirst().orElseThrow(() -> new RuntimeException("Field not found"));
        assertEquals(String.class, firstName.getType());

    }

    @Test
    public void testReflectionWithInheritance() {
        List<Field> personFields = Arrays.asList(Employee.class.getSuperclass().getDeclaredFields());
        List<Field> employeeFields = Arrays.asList(Employee.class.getDeclaredFields());

        List<Field> allFields = Stream.concat(personFields.stream(), employeeFields.stream())
                .collect(Collectors.toList());

        assertEquals(4, allFields.size());
        Field lastNameField = allFields.stream()
                .filter(field -> field.getName().equals(LAST_NAME_FIELD))
                .findFirst().orElseThrow(() -> new RuntimeException("Field not found"));
        assertEquals(String.class, lastNameField.getType());
        Field firstNameField = allFields.stream()
                .filter(field -> field.getName().equals(FIRST_NAME_FIELD))
                .findFirst().orElseThrow(() -> new RuntimeException("Field not found"));
        assertEquals(String.class, firstNameField.getType());
        Field employeeIdField = allFields.stream()
                .filter(field -> field.getName().equals(EMPLOYEE_ID_FIELD))
                .findFirst().orElseThrow(() -> new RuntimeException("Field not found"));
        assertEquals(employeeIdField.getType(), int.class);


    }
}
