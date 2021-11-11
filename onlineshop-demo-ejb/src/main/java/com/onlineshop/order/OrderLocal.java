package com.onlineshop.order;

import java.util.List;

import javax.ejb.Local;

import com.onlineshop.model.Orders;

@Local
public interface OrderLocal {
	
	List<Orders> getOrdersByMonth(int month);

}
