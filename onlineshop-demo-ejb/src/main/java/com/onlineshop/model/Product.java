package com.onlineshop.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name=Product.TABLE_NAME)
public class Product {
	public static final String TABLE_NAME = "PRODUCT";
	
	@Id
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PRICE")
	private BigDecimal price;
	
	@Column(name="DELETED_TS")
	private Timestamp deletedTs;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_INVENTORY_ID")
	private ProductInventory productInventory;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_CATEGORY_ID")
	private ProductCategory productCategory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getDeletedTs() {
		return deletedTs;
	}

	public void setDeletedTs(Timestamp deletedTs) {
		this.deletedTs = deletedTs;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductInventory getProductInventory() {
		return productInventory;
	}

	public void setProductInventory(ProductInventory productInventory) {
		this.productInventory = productInventory;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

}
