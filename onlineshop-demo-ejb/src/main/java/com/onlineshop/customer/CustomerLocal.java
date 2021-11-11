package com.onlineshop.customer;

import javax.ejb.Local;

import com.onlineshop.model.Customer;
@Local
public interface CustomerLocal {
	
	Customer getCustomer(int id);

}
