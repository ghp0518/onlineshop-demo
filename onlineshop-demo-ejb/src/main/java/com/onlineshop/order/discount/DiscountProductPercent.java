package com.onlineshop.order.discount;

import java.math.BigDecimal;
import java.util.List;

import com.onlineshop.model.CustomerDiscountProductPercent;
import com.onlineshop.order.OrderItemPrice;
import com.onlineshop.order.OrderTotalDetail;

public class DiscountProductPercent implements Discounter {
	
	private int discountId;
	
	private BigDecimal percent;
	
	private int productId;
	
	
	/**
	 * Product percent discounter
	 * 
	 * @param discountId The related discount id
	 * @param percent The discount percent
	 * @param productId The Product id for which the discount is applied
	 */
	public DiscountProductPercent(int discountId, BigDecimal percent, int productId) {
		super();
		this.discountId = discountId;
		this.percent = percent;
		this.productId = productId;
	}
	
	public DiscountProductPercent(CustomerDiscountProductPercent customerDiscount) {
		 this(customerDiscount.getId(), new BigDecimal(customerDiscount.getDiscountPercent()), customerDiscount.getProduct().getId());
	}

	@Override
	public OrderTotalDetail orderTotalDiscount(List<OrderItemPrice> orderItems) {
		OrderTotalDetail totalDetail = new OrderTotalDetail();
		BigDecimal total = new BigDecimal(0);
		BigDecimal discount = new BigDecimal(0);
		for(OrderItemPrice order : orderItems) {
			if (order.basePrice != null) {
				if(order.productId == productId) {
					discount = order.basePrice.multiply(percent).movePointLeft(2).multiply(new BigDecimal(order.quantity));
				}
				total = total.add(order.basePrice.multiply(new BigDecimal(order.quantity)));
			}
		}
		totalDetail.discount = discount;
		totalDetail.totalWithDiscount = total.subtract(discount);
		return totalDetail;
	}

	@Override
	public DiscountType getDiscountType() {
		return DiscountType.PercentProduct;
	}

	@Override
	public int getDiscountId() {
		return this.discountId;
	}

}
