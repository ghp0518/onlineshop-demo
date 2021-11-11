package com.onlineshop.customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.onlineshop.model.Customer;

@Stateless(name="CustomerEjb")
public class CustomerEjb implements CustomerLocal {
	
	@PersistenceContext(unitName = "onlineShopDB")
    private EntityManager em;

	@Override
	public Customer getCustomer(int id) {

		return em.find(Customer.class, id);
	}

}
