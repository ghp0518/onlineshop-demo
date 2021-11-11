package com.onlineshop.order.discount;

import java.math.BigDecimal;
import java.util.List;

import com.onlineshop.model.CustomerDiscountProductQuantity;
import com.onlineshop.order.OrderItemPrice;
import com.onlineshop.order.OrderTotalDetail;

public class DiscountProductQuantity implements Discounter {

	private int discountId;

	private int buyQuantity;

	private int payQuantity;

	private int productId;
	
	
	/**
	 * Product quantity discounter 
	 * 
	 * @param discountId The related discount id
	 * @param buyQuantity The minimum quantity you have to buy for this quantity to be applied
	 * @param payQuantity The quantity you pay for, related to the buyQuantity
	 * @param productId The Product id for which the discount is applied
	 */
	public DiscountProductQuantity(int discountId, int buyQuantity, int payQuantity, int productId) {
		super();
		this.discountId = discountId;
		this.buyQuantity = buyQuantity;
		this.payQuantity = payQuantity;
		this.productId = productId;
	}
	
	public DiscountProductQuantity(CustomerDiscountProductQuantity customerDiscount) {
		this(customerDiscount.getId(), customerDiscount.getBuyQuantity(), customerDiscount.getPayQuantity(), customerDiscount.getProduct().getId());
	}

	@Override
	public OrderTotalDetail orderTotalDiscount(List<OrderItemPrice> orderItems) {
		OrderTotalDetail totalDetail = new OrderTotalDetail();
		BigDecimal total = new BigDecimal(0);
		BigDecimal discount = new BigDecimal(0);
		int productDifference = buyQuantity - payQuantity;
		for (OrderItemPrice order : orderItems) {
			if (order.basePrice != null) {
				if (order.productId == productId) {
					if (order.quantity > buyQuantity) {
						int discountTimes = order.quantity / buyQuantity;
						BigDecimal discountedQuantity = new BigDecimal(discountTimes * productDifference);
						discount = order.basePrice.multiply(discountedQuantity);
					}
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
		return DiscountType.QuantityProduct;
	}

	@Override
	public int getDiscountId() {
		return this.discountId;
	}

}
