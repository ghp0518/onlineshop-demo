package com.onlineshop.order.discount;

import java.math.BigDecimal;
import java.util.List;

import com.onlineshop.order.OrderItemPrice;
import com.onlineshop.order.OrderTotalDetail;

public class NoDiscount implements Discounter {

	@Override
	public OrderTotalDetail orderTotalDiscount(List<OrderItemPrice> orderItems) {
		OrderTotalDetail totalDetail = new OrderTotalDetail();
		totalDetail.discount = new BigDecimal(0);
		totalDetail.totalWithDiscount = new BigDecimal(0);
		for(OrderItemPrice order : orderItems) {
			if (order.basePrice != null) {
				totalDetail.totalWithDiscount = totalDetail.totalWithDiscount.add(order.basePrice.multiply(new BigDecimal(order.quantity)));
			}
		}
		return totalDetail;
	}

	@Override
	public DiscountType getDiscountType() {
		return null;
	}

	@Override
	public int getDiscountId() {
		return 0;
	}

}
