package com.onlineshop.product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.onlineshop.model.Product;

@Stateless
public class ProductEjb implements ProductLocal {
	@PersistenceContext(unitName = "onlineShopDB")
    private EntityManager em;

	@Override
	public Product getProduct(int id) {
		return em.find(Product.class, id);
	}

}
