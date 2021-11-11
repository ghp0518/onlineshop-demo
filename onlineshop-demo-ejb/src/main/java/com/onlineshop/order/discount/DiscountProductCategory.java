package com.onlineshop.order.discount;

import java.math.BigDecimal;
import java.util.List;

import com.onlineshop.order.OrderItemPrice;
import com.onlineshop.order.OrderTotalDetail;

public class DiscountProductCategory implements Discounter {
	
	public DiscountProductCategory(int discountId, BigDecimal percent, String categoryCode) {
		super();
		this.discountId = discountId;
		this.percent = percent;
		this.categoryCode = categoryCode;
	}

	private int discountId;
	
	private BigDecimal percent;
	
	public String categoryCode;
	
	

	@Override
	public OrderTotalDetail orderTotalDiscount(List<OrderItemPrice> orderItems) {
		// TODO Implement Logic
		return null;
	}

	@Override
	public DiscountType getDiscountType() {
		return DiscountType.CategoryProduct;
	}

	@Override
	public int getDiscountId() {
		return discountId;
	}

}
