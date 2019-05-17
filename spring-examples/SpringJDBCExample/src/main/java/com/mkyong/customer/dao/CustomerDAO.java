package com.mkyong.customer.dao;

import com.mkyong.customer.model.Customer;

public interface CustomerDAO 
{
	 void insert(Customer customer);
	 Customer findByCustomerId(int custId);
}




