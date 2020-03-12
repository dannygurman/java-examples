package hibernateexample.dao;

import hibernateexample.model.Customer;

import java.util.List;


public interface CustomerDao {
    public List<Customer> getAllCustomers() ;

    public Customer getCustomer(int id) ;

    public Customer addCustomer(Customer customer);

    public void updateCustomer(Customer customer) ;

    public void deleteCustomer(int id) ;
}
