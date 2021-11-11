package com.onlineshop.order.discount;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.onlineshop.model.CustomerDiscountProductPercent;
import com.onlineshop.model.CustomerDiscountProductQuantity;
import com.onlineshop.model.CustomerDiscountTotal;

@Component
public class DiscountFactory {

	public Discounter getDiscount(CustomerDiscountTotal discount ) {
		return new DiscountTotalPercent(discount);
	}
	
	public Discounter getDiscount(CustomerDiscountProductPercent discount ) {
		return new DiscountProductPercent(discount);
	}	
	
	public Discounter getDiscount(CustomerDiscountProductQuantity discount ) {
		return null;
	}
	
	public List<Discounter> getDiscountListTotal(List<CustomerDiscountTotal> discountList){
		List<Discounter> discounterList = new ArrayList<Discounter>();
		for(CustomerDiscountTotal discount : discountList) {
			discounterList.add(getDiscount(discount));
		}	
		return discounterList;
	}
	
	public List<Discounter> getDiscountListProductPercent(List<CustomerDiscountProductPercent> discountList){
		List<Discounter> discounterList = new ArrayList<Discounter>();
		for(CustomerDiscountProductPercent discount : discountList) {
			discounterList.add(getDiscount(discount));
		}	
		return discounterList;
	}
	
	public List<Discounter> getDiscountListProductQuantity(List<CustomerDiscountProductQuantity> discountList){
		List<Discounter> discounterList = new ArrayList<Discounter>();
		for(CustomerDiscountProductQuantity discount : discountList) {
			discounterList.add(getDiscount(discount));
		}	
		return discounterList;
	}
}
