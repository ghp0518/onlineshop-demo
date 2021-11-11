package com.onlineshop.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name=CustomerDiscountTotal.ENTITY_NAME)
@Table(name=CustomerDiscountTotal.TABLE_NAME)
@NamedQueries({
	@NamedQuery(name = CustomerDiscountTotal.QRY_FIND_BY_CUSTOMER ,
	            query = "select o from "+ CustomerDiscountTotal.ENTITY_NAME + " o " +
	            		"where o.deletedTs is null and o.customer.id = :customerId ")
	
})
public class CustomerDiscountTotal {
	
	public static final String ENTITY_NAME = "CustomerDiscountTotal";
	
	public static final String TABLE_NAME = "CUSTOMER_DISCOUNT_TOTAL";
	
	public static final String QRY_FIND_BY_CUSTOMER = ENTITY_NAME + ".findByCustomer";
	
	@Id
	private int id;
	
	@Column(name="DISCOUNT_CODE")
	private String discountCode;
	
	@Column(name="DELETED_TS")
	private Timestamp deletedTs;
	
	
	@Column(name="DISCOUNT_PERCENT")
	private int discountPercent;
	
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

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

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
}
