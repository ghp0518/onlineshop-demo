package com.onlineshop.order;

import com.onlineshop.exception.ResourceNotFoundException;

/**
 * Service facade interface to 
 * @author CSavin
 *
 */
public interface OrderService {
	
	/**
	 * Validates a customer order request against an existing customer and the stock inventory for the products in this order
	 * 
	 * @param request
	 * 		  The customer order request
	 * 
	 * @throws {@link ResourceNotFoundException} in case the customer does not exist
	 * 		   this is set to a response status HttpStatus.NOT_FOUND
	 * 
	 * @throws {@link ProductInventoryException} in case the product inventory for any product in the order 
	 *         is smaller than the quantity from the order 
	 *         this is set to a response status HttpStatus.BAD_REQUEST
	 */
	public void validateOrderRequest(OrderRequest request);
	
	/**
	 * Returns the {@link OrderTotalDetail}, representing the total order value and discount, 
	 * applying the best discount available  the orderRequest
	 * 
	 * @param 	request
	 * 			The customer order request
	 * @return	the {@link OrderTotalDetail}, representing the total order value and discount, 
	 *			applying the best discount available
	 */
	public OrderTotalDetail getOrderTotal(OrderRequest request);

}
