package com.onlineshop.order.discount;

import java.util.ArrayList;
import java.util.List;


import com.onlineshop.order.OrderItemPrice;
import com.onlineshop.order.OrderTotalDetail;
/**
 * Discounter interface to apply different discount logic to a list of {@link OrderItemPrice}
 * @author CSavin
 *
 */
public interface Discounter {
	enum DiscountType {
	    PercentTotal,
	    PercentProduct,
	    QuantityProduct,
	    CategoryProduct
	  }
	OrderTotalDetail orderTotalDiscount(List<OrderItemPrice> orderItems);
	
	DiscountType getDiscountType();
	
	int getDiscountId();
	
	public static OrderTotalDetail applyBestDiscountOrder (List<OrderItemPrice> orderItems, List<Discounter> availableDiscounts) {
		OrderTotalDetail bestDiscountTotal = null;
		
		List<Discounter> applicableDiscount = new ArrayList<Discounter>();
		//default no discount if availableDiscounts is null or empty
		applicableDiscount.add(new NoDiscount());
		

		if(availableDiscounts != null && !availableDiscounts.isEmpty()) {
			applicableDiscount.addAll(availableDiscounts);
		}
		for(Discounter discount : applicableDiscount) {
			if(bestDiscountTotal == null) {
				bestDiscountTotal = discount.orderTotalDiscount(orderItems);
			}
			else {
				OrderTotalDetail orderTotalDetail = discount.orderTotalDiscount(orderItems);
				if(orderTotalDetail.discount.compareTo(bestDiscountTotal.discount) > 0) {
					bestDiscountTotal = orderTotalDetail;
				}
			}
		}
		return bestDiscountTotal;
	}
		

}
