package com.onlineshop.product;

import javax.ejb.Local;

import com.onlineshop.model.Product;

@Local
public interface ProductLocal {
	Product getProduct(int id);
}
