package com.onlineshop.order;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.onlineshop.customer.CustomerLocal;
import com.onlineshop.discount.DiscountLocal;
import com.onlineshop.exception.ProductInventoryException;
import com.onlineshop.exception.ResourceNotFoundException;
import com.onlineshop.model.Customer;
import com.onlineshop.model.CustomerDiscountProductPercent;
import com.onlineshop.model.CustomerDiscountProductQuantity;
import com.onlineshop.model.CustomerDiscountTotal;
import com.onlineshop.model.Product;
import com.onlineshop.order.discount.DiscountFactory;
import com.onlineshop.order.discount.Discounter;
import com.onlineshop.product.ProductLocal;

@Service
public class OrderServiceImpl implements OrderService {

	
	private CustomerLocal customerLocal;

	private ProductLocal productLocal;
	
	private DiscountLocal discountLocal;
	
	@Inject
	private DiscountFactory discountFactory;
	
	
	//dependency injection on method or constructor is preferable to field injection
	//if you want to override them with mock versions for unit testing
	
	
	@EJB(mappedName = "java:global/onlineshop-demo-ear/onlineshop-demo-ejb/CustomerEjb")
	public void setCustomerLocal(CustomerLocal customerLocal) {
		this.customerLocal = customerLocal;
	}

	@EJB(mappedName = "java:global/onlineshop-demo-ear/onlineshop-demo-ejb/ProductEjb")
	public void setProductLocal(ProductLocal productLocal) {
		this.productLocal = productLocal;
	}

	@EJB(mappedName = "java:global/onlineshop-demo-ear/onlineshop-demo-ejb/DiscountEjb")
	public void setDiscountLocal(DiscountLocal discountLocal) {
		this.discountLocal = discountLocal;
	}

	@Override
	public void validateOrderRequest(OrderRequest request) {
		Customer c = customerLocal.getCustomer(request.customerId);
		if (c == null) {
			throw new ResourceNotFoundException("Customer not found!");
		}
		for (OrderItem item : request.orderItems) {
			verifyItem(item);
		}

	}


	@Override
	public OrderTotalDetail getOrderTotal(OrderRequest request) {
		List<OrderItemPrice> itemsWithPrice = getOrderItemBasePrice(request.orderItems);
		List<Integer> productIdList = getProductIdList(request.orderItems);
		List<CustomerDiscountTotal> discountTotal = discountLocal.getAvailableCustomerDiscountTotal(request.customerId);
		List<CustomerDiscountProductPercent> discountProductPercent = discountLocal
				.getAvailableCustomerDiscountProductPercent(request.customerId, productIdList);
		List<CustomerDiscountProductQuantity> discountProductQuantity = discountLocal
				.getAvailableCustomerDiscountProductQuantity(request.customerId, productIdList);
		List<Discounter> discounterList = new ArrayList<Discounter>();
		discounterList.addAll(discountFactory.getDiscountListTotal(discountTotal));
		discounterList.addAll(discountFactory.getDiscountListProductQuantity(discountProductQuantity));
		discounterList.addAll(discountFactory.getDiscountListProductPercent(discountProductPercent));
		
		return Discounter.applyBestDiscountOrder(itemsWithPrice, discounterList);
	}

	private List<Integer> getProductIdList(List<OrderItem> orderItems) {
		List<Integer> productIdList = new ArrayList<Integer>();
		for (OrderItem item : orderItems) {
			productIdList.add(item.productId);
		}
		return productIdList;
	}

	private List<OrderItemPrice> getOrderItemBasePrice(List<OrderItem> orderItems) {
		List<OrderItemPrice> orderItemPrices = new ArrayList<OrderItemPrice>();
		for (OrderItem item : orderItems) {
			Product product = productLocal.getProduct(item.productId);
			if (product != null && product.getDeletedTs() == null) {
				OrderItemPrice itemPrice = new OrderItemPrice(item);
				itemPrice.basePrice = product.getPrice();
				itemPrice.categoryCode = product.getProductCategory() != null ? product.getProductCategory().getCategoryCode() : null;
				orderItemPrices.add(itemPrice);
			}
		}
		return orderItemPrices;
		
	}
	
	private void verifyItem(OrderItem item) {
		Product product = productLocal.getProduct(item.productId);
		if (product == null || product.getDeletedTs() != null) {
			throw new ResourceNotFoundException("Product not found!");
		}

		if (product.getProductInventory() == null
				|| product.getProductInventory().getDeletedTs() != null
				|| product.getProductInventory().getQuantity() == null
				|| product.getProductInventory().getQuantity() < item.quantity) {
			throw new ProductInventoryException("Product: " + product.getName() + " does not meet the requirements");
		}
	}

}
