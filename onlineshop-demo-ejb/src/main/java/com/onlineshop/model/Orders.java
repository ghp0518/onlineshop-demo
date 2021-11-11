package com.onlineshop.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = Orders.ENTITY_NAME)
@Table(name = Orders.TABLE_NAME)
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "getAllEmployees",
        query = "SELECT * FROM "+ Orders.TABLE_NAME + " where MONTH(CREATED_TS) = ?1",
                    resultClass=Orders.class
    )
})
public class Orders {
    	

    public static final String ENTITY_NAME = "Orders";
    
	public static final String TABLE_NAME = "ORDERS";
	
	public static final String QRY_NATIVE_FIND_BY_MONTH = ENTITY_NAME + ".findByMonth";
	
	@Id
	private int id;
	
	@Column(name="CREATED_TS")
	private Timestamp createdTs;
	
	@Column(name="DELETED_TS")
	private Timestamp deletedTs;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;
	
	@Column(name="DISCOUNT_CODE")
	private String discountCode;
	
	@Column(name="TOTAL_PRICE")
	private BigDecimal totalPrice;
	
	@Column(name="DISCOUNT")
	private BigDecimal discount;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItems> orderItems;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Timestamp createdTs) {
		this.createdTs = createdTs;
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

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

}
