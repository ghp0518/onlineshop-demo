package com.onlineshop.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name=ProductInventory.ENTITY_NAME)
@Table(name=ProductInventory.TABLE_NAME)

public class ProductInventory {
	
	public static final String ENTITY_NAME = "OrderDistPointDetail";
	
	public static final String TABLE_NAME = "PRODUCT_INVENTORY";
	
	@Id
	private int id;
	
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@Column(name="DELETED_TS")
	private Timestamp deletedTs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Timestamp getDeletedTs() {
		return deletedTs;
	}

	public void setDeletedTs(Timestamp deletedTs) {
		this.deletedTs = deletedTs;
	}

}
