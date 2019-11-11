package com.fullstackdevelopment.myeshop.dao;

import com.fullstackdevelopment.myeshop.model.Customer;

public interface CustomerDAO extends GenericDAO<Customer>{
	Customer findCustomerByEmail(String email);
}
