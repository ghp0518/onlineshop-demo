package com.onlineshop.order;

import java.math.BigDecimal;

/**
 *  logic-less object representation of an order item
 *  (no setters/getters : Keep It Stupid Simple)
 * 
 * 
 * @author CSavin
 *
 */
public class OrderItemPrice extends OrderItem {
	
	/**
	 * Constructor from fields
	 * 
	 * @param productId The product id of the order item
	 * @param quantity The quantity of products
	 * @param basePrice The bas price of the product
	 * @param categoryCode The product category code
	 */
	public OrderItemPrice(int productId, int quantity, BigDecimal basePrice, String categoryCode) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.basePrice = basePrice;
		this.categoryCode = categoryCode;
	}

	public OrderItemPrice() {
		super();
	}

	public OrderItemPrice(OrderItem item) {
		super();
		this.productId = item.productId;
		this.quantity = item.quantity;
	}

	public BigDecimal basePrice;
	public String categoryCode;

}
