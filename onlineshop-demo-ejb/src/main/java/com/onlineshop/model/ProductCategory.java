package com.onlineshop.model;

import javax.persistence.*;

@Entity
@Table(name=ProductCategory.TABLE_NAME)
public class ProductCategory  {
	
	public static final String TABLE_NAME = "PRODUCT_CATEGORY";

	@Id
	private int id;
	
	@Column(name="CATEGORY_CODE")
	private String categoryCode;
	
	@Column(name="DESCRIPTION")
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
   
}
