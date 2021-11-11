package com.onlineshop.order.discount;

import java.math.BigDecimal;
import java.util.List;

import com.onlineshop.model.CustomerDiscountTotal;
import com.onlineshop.order.OrderItemPrice;
import com.onlineshop.order.OrderTotalDetail;

public class DiscountTotalPercent implements Discounter {
	
	private int discountId;
	
	private BigDecimal percent;
	
	/**
	 * Total percent discounter
	 * 
	 * @param discountId The related discount id
	 * @param percent The discount percent
	 */
	public DiscountTotalPercent(int discountId, BigDecimal percent) {
		super();
		this.discountId = discountId;
		this.percent = percent;
	}
	
	
	public DiscountTotalPercent(CustomerDiscountTotal customerDiscount) {
	   this(customerDiscount.getId(), new BigDecimal(customerDiscount.getDiscountPercent()));
	}

	@Override
	public OrderTotalDetail orderTotalDiscount(List<OrderItemPrice> orderItems) {
		OrderTotalDetail totalDetail = new OrderTotalDetail();
		BigDecimal total = new BigDecimal(0);
		for(OrderItemPrice order : orderItems) {
			if (order.basePrice != null) {
				total = total.add(order.basePrice.multiply(new BigDecimal(order.quantity)));
			}
		}
		totalDetail.discount = total.multiply(percent).movePointLeft(2);
		totalDetail.totalWithDiscount = total.subtract(totalDetail.discount);
		
		return totalDetail;
	}

	@Override
	public DiscountType getDiscountType() {
		return DiscountType.PercentTotal;
	}

	@Override
	public int getDiscountId() {
		return discountId;
	}

}
