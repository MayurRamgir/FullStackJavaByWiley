package com.fullstackdevelopment.myeshop.dao.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fullstackdevelopment.myeshop.dao.CustomerDAO;
import com.fullstackdevelopment.myeshop.model.Address;
import com.fullstackdevelopment.myeshop.model.Customer;

public class CustomerDAOImpl implements CustomerDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	public Customer get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long save(Customer t) {
		Customer customer = t;

		if (customer == null) {
			customer = new Customer();
			customer.setName("Albert Einstein");
			customer.setEmail("Albert@Einstein.com");

			// set date of birth
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			try {
				Date parsed = format.parse("1879014");
				Timestamp dateOfBirth = new Timestamp(parsed.getTime());
				customer.setDateOfBirth(dateOfBirth);
			} catch (ParseException e) {
				System.out.println("Error in parsing the date of birth");
				e.printStackTrace();
			}

			// set billing and shipping addresses
			List<Address> allAddresses = new ArrayList<Address>();

			Address shippingAddress = new Address();
			shippingAddress.setAddressLine1("112");
			shippingAddress.setAddressLine2("Mercer St");
			shippingAddress.setCity("Princeton");
			shippingAddress.setZipcode("NJ 08540");
			shippingAddress.setCountry("USA");
			sessionFactory.getCurrentSession().save(shippingAddress);
			allAddresses.add(shippingAddress);

			Address billingAddress = new Address();
			billingAddress.setAddressLine1("112");
			billingAddress.setAddressLine2("Mercer St");
			billingAddress.setCity("Princeton");
			billingAddress.setZipcode("NJ 08540");
			billingAddress.setCountry("USA");
			sessionFactory.getCurrentSession().save(billingAddress);
			allAddresses.add(billingAddress);

			customer.setAddresses(allAddresses);
		}

		sessionFactory.getCurrentSession().save(customer);
		sessionFactory.getCurrentSession().flush();

		return customer.getCustomerid();
	}

	@Override
	public void update(Customer t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Customer t) {
		Customer customer = t;
		if(customer != null) {
			sessionFactory.getCurrentSession().delete(customer);
		}
	}

	@Override
	public Customer findCustomerByEmail(String email) {
		CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
		Root<Customer> root = criteriaQuery.from(Customer.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("email"), email));
		Query<Customer> q = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
		return q.getSingleResult();
	}

}
