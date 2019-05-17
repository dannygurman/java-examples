package lambdaAndStream.java.utils.functional.suppliers;

import lambdaAndStream.Person;

import java.util.function.Supplier;

//* A supplier provide an object , take no parameters

public class SupplierMain {

    Supplier<Person> personSupplier = () -> new Person();
    Supplier <Person> personSupplier2 = Person::new;
}
