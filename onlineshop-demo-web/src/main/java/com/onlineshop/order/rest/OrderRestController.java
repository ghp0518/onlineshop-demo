package com.onlineshop.order.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.order.OrderRequest;
import com.onlineshop.order.OrderService;
import com.onlineshop.order.OrderTotalDetail;

@RestController
@RequestMapping(OrderRestController.BASE_URI)
public class OrderRestController {
	public static final String BASE_URI = "/api/order";
	
	@Autowired 
	OrderService orderService;
	
	 @PostMapping(path="totalDiscount",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public OrderTotalDetail getOrderTotalDiscount(@RequestBody OrderRequest orderItems) {
		 orderService.validateOrderRequest(orderItems);
		 return orderService.getOrderTotal(orderItems);
	 }
}
