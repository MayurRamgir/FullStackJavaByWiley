package com.fullstackdevelopment.myeshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fullstackdevelopment.myeshop.dao.CustomerDAO;
import com.fullstackdevelopment.myeshop.model.Customer;

@Controller
public class CustomerController {

	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping(value = "/customer/getCustomerByEmail/{email}", method = RequestMethod.GET)
	public Customer findCustomerByEmail(@PathVariable("email") String email) {
		
		Customer customer = customerDAO.findCustomerByEmail(email);
		return customer;
	
	}
}
