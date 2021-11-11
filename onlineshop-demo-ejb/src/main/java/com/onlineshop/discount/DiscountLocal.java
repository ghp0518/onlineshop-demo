package com.onlineshop.discount;

import java.util.List;

import javax.ejb.Local;

import com.onlineshop.model.CustomerDiscountProductPercent;
import com.onlineshop.model.CustomerDiscountProductQuantity;
import com.onlineshop.model.CustomerDiscountTotal;

@Local
public interface DiscountLocal {
	
	public List<CustomerDiscountTotal> getAvailableCustomerDiscountTotal(int customerId);
	
	public List<CustomerDiscountProductPercent> getAvailableCustomerDiscountProductPercent(int customerId , List<Integer> productIdList);

	public List<CustomerDiscountProductQuantity> getAvailableCustomerDiscountProductQuantity(int customerId , List<Integer> productIdList);

}
