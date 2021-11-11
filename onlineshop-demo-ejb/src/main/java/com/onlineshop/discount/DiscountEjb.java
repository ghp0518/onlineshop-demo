package com.onlineshop.discount;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.onlineshop.model.CustomerDiscountProductPercent;
import com.onlineshop.model.CustomerDiscountProductQuantity;
import com.onlineshop.model.CustomerDiscountTotal;

@Stateless
public class DiscountEjb implements DiscountLocal{
	
	@PersistenceContext(unitName = "onlineShopDB")
    private EntityManager em;

	@Override
	public List<CustomerDiscountTotal> getAvailableCustomerDiscountTotal(int customerId) {
		TypedQuery<CustomerDiscountTotal> query = em.createNamedQuery(CustomerDiscountTotal.QRY_FIND_BY_CUSTOMER, CustomerDiscountTotal.class);
		query.setParameter("customerId", customerId);
		
		return query.getResultList();
	}

	@Override
	public List<CustomerDiscountProductPercent> getAvailableCustomerDiscountProductPercent(int customerId,
			List<Integer> productIdList) {
		TypedQuery<CustomerDiscountProductPercent> query = em.createNamedQuery(CustomerDiscountProductPercent.QRY_FIND_BY_CUSTOMER_PRODUCTS, CustomerDiscountProductPercent.class);
		query.setParameter("customerId", customerId);
		query.setParameter("productIdList", productIdList);
		
		return query.getResultList();
	}

	@Override
	public List<CustomerDiscountProductQuantity> getAvailableCustomerDiscountProductQuantity(int customerId,
			List<Integer> productIdList) {
		TypedQuery<CustomerDiscountProductQuantity> query = em.createNamedQuery(CustomerDiscountProductQuantity.QRY_FIND_BY_CUSTOMER_PRODUCTS, CustomerDiscountProductQuantity.class);
		query.setParameter("customerId", customerId);
		query.setParameter("productIdList", productIdList);
		
		return query.getResultList();
	}

}
