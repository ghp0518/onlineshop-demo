package com.onlineshop.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name=CustomerDiscountProductQuantity.ENTITY_NAME)
@Table(name=CustomerDiscountProductQuantity.TABLE_NAME)

@NamedQueries({
	@NamedQuery(name = CustomerDiscountProductQuantity.QRY_FIND_BY_CUSTOMER_PRODUCTS ,
	            query = "select o from "+ CustomerDiscountProductQuantity.ENTITY_NAME + " o " +
	            		"where o.deletedTs is null and  o.customer.id = :customerId and o.product.id IN :productIdList")
	
})
public class CustomerDiscountProductQuantity {
	
	public static final String ENTITY_NAME = "CustomerDiscountProductQuantity";
	public static final String TABLE_NAME = "CUSTOMER_DISCOUNT_PRODUCT_QUANTITY";
	public static final String QRY_FIND_BY_CUSTOMER_PRODUCTS = ENTITY_NAME + ".findByCustomerProducts";
	
	@Id
	private int id;
	
	@Column(name="DISCOUNT_CODE")
	private String discountCode;
	
	@Column(name="DELETED_TS")
	private Timestamp deletedTs;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	
	@Column(name="BUY_QUANTITY")
	private int buyQuantity;
	
	@Column(name="PAY_QUANTITY")
	private int payQuantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public Timestamp getDeletedTs() {
		return deletedTs;
	}

	public void setDeletedTs(Timestamp deletedTs) {
		this.deletedTs = deletedTs;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public int getPayQuantity() {
		return payQuantity;
	}

	public void setPayQuantity(int payQuantity) {
		this.payQuantity = payQuantity;
	}	

}
