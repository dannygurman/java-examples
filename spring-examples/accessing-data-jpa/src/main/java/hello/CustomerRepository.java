package hello;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


//CrudRepository - Interface for generic CRUD operations on a repository for a specific type.

//The goal of Spring Data repository abstraction is to significantly reduce the amount of boilerplate code
// required to implement data access layers for various persistence stores.

//By simply adding a method to the repository, it will generate the right code and map
// everything to a SQL.
// Because CrudRepository already has few basic methods prebuilt,
// we don’t need to add a method to find all Customer. Instead, we can use findAll method.

//But to find all customers by last name  we have to define our custom method. Actually, it’s very simple.

//Spring Data will build the query based on method name and method return type!

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
