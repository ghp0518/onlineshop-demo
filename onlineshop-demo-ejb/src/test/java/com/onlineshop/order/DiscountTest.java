package com.onlineshop.order;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.onlineshop.order.discount.DiscountProductPercent;
import com.onlineshop.order.discount.DiscountProductQuantity;
import com.onlineshop.order.discount.DiscountTotalPercent;
import com.onlineshop.order.discount.Discounter;

@TestInstance(Lifecycle.PER_CLASS)
class DiscountTest {
	
	List<OrderItemPrice> orderItems;
	List<Discounter> totalDiscounter;
	List<Discounter> productPercentDiscounter;
	List<Discounter> productQuantityDiscounter;
	List<Discounter> mixedDiscounter;
	
	
	@BeforeAll
    public void setupBeforeClass() {
		initializeMockOrder();
		initializeTotalDiscounter();
		initializeProductPercentDiscounter();
		initializeProductQuantityDiscounter();
		initializeMixedDiscounter();
		
	}

	private void initializeMixedDiscounter() {
		mixedDiscounter = new ArrayList<Discounter>();
		//add all the previous discounts
		mixedDiscounter.addAll(totalDiscounter);
		mixedDiscounter.addAll(productPercentDiscounter);
		mixedDiscounter.addAll(productQuantityDiscounter);	
	}

	private void initializeProductQuantityDiscounter() {
		productQuantityDiscounter = new ArrayList<Discounter>();
		//buy 3 of the cheaper chairs pay for 2
		productQuantityDiscounter.add(new DiscountProductQuantity(1, 3, 2, 5));
		
	}

	private void initializeProductPercentDiscounter() {
		productPercentDiscounter = new ArrayList<Discounter>();
		//50% discount on the sofa
		productPercentDiscounter.add(new DiscountProductPercent(1,new BigDecimal(50),3));
		
	}

	private void initializeTotalDiscounter() {
		totalDiscounter = new ArrayList<Discounter>();
		totalDiscounter.add(new DiscountTotalPercent(1, new BigDecimal(20)));
		
	}

	private void initializeMockOrder() {
		 //Create a mock order representation	
		 orderItems = new ArrayList<OrderItemPrice>();
		 
		 //2 chairs priced 80 (product id 1)
		 orderItems.add(new OrderItemPrice(1,2,new BigDecimal(80), "CHAIR"));
		 
		 //2 desks priced 200 (product id 2)
		 orderItems.add(new OrderItemPrice(2,2,new BigDecimal(200), "DESK"));
		 
		 //1 sofa priced 600 (product id 3)
		 orderItems.add(new OrderItemPrice(3,1,new BigDecimal(600), "SOFA"));
		 
		 //2 cabinets priced 30 (product id 4)
		 orderItems.add(new OrderItemPrice(4,2,new BigDecimal(30), "CABINET"));
		 
		 //20 cheaper chairs (product id 5)
		 orderItems.add(new OrderItemPrice(5,20, new BigDecimal(40), "CHAIR"));
		 
		 //undiscounted total price = 2020;
		
	}

	@Test
	void testDiscountPercentageTotal() {
		
		//get best discount order total
		OrderTotalDetail orderTotalDetail = Discounter.applyBestDiscountOrder(orderItems, totalDiscounter);
		
		//use BigDecimal.compareTo for value comparison since equals can return false for BigDecimal holding the same value
		
		//20% discount value out of 2020$
		assertTrue(orderTotalDetail.discount.compareTo(new BigDecimal(404)) == 0);
		
		//total value minus 20%
		assertTrue(orderTotalDetail.totalWithDiscount.compareTo(new BigDecimal(1616)) == 0);
	}
	
	@Test
	void testDiscountPercentageProduct() {
		//get best discount order total
		OrderTotalDetail orderTotalDetail = Discounter.applyBestDiscountOrder(orderItems, productPercentDiscounter);
		
		//use BigDecimal.compareTo for value comparison since equals can return false for BigDecimal holding the same value
		
		//50% discount on the 600$ sofa
		assertTrue(orderTotalDetail.discount.compareTo(new BigDecimal(300)) == 0);
		
		//total value minus sofa discount
		assertTrue(orderTotalDetail.totalWithDiscount.compareTo(new BigDecimal(1720)) == 0);

	}
	
	@Test
	void testDiscountQuantityProduct() {
		//get best discount order total
		OrderTotalDetail orderTotalDetail = Discounter.applyBestDiscountOrder(orderItems, productQuantityDiscounter);
		
		//buy 3 pay 2 discount on the 20 x 40$ chair (buy 20 pay for 14)
		assertTrue(orderTotalDetail.discount.compareTo(new BigDecimal(240)) == 0);
				
		//total value minus chair discount
		assertTrue(orderTotalDetail.totalWithDiscount.compareTo(new BigDecimal(1780)) == 0);
	}
	
	@Test
	void testDiscountMixed() {
		//get best discount order total out of all discounts
		OrderTotalDetail orderTotalDetail = Discounter.applyBestDiscountOrder(orderItems, mixedDiscounter);

		assertTrue(orderTotalDetail.discount.compareTo(new BigDecimal(404)) == 0);
		assertTrue(orderTotalDetail.totalWithDiscount.compareTo(new BigDecimal(1616)) == 0);
	}
	
	@Test
	void testNoDiscount() {
		//test when no discount is available
		OrderTotalDetail orderTotalDetail = Discounter.applyBestDiscountOrder(orderItems, null);

		assertTrue(orderTotalDetail.discount.compareTo(new BigDecimal(0)) == 0);
		assertTrue(orderTotalDetail.totalWithDiscount.compareTo(new BigDecimal(2020)) == 0);
	}

}
