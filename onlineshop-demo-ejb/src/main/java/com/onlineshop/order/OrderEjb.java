package com.onlineshop.order;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.onlineshop.model.Orders;

public class OrderEjb implements OrderLocal{
	@PersistenceContext(unitName = "onlineShopDB")
    private EntityManager em;

	@Override
	public List<Orders> getOrdersByMonth(int month) {
		TypedQuery<Orders> q = em.createNamedQuery(Orders.QRY_NATIVE_FIND_BY_MONTH, Orders.class);
		q.setParameter(1, month);
		return q.getResultList();
	}

}
