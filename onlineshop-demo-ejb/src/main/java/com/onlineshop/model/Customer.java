package com.onlineshop.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = Customer.TABLE_NAME)
public class Customer {
	
	public static final String TABLE_NAME = "CUSTOMER";
	
	@Id
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DELETED_TS")
	private Timestamp deletedTs;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PHONE")
	private String phone;
	
	@Column(name="ADDRESS")
	private String address;
	
	@OneToMany(mappedBy = "customer")
	List<CustomerDiscountTotal> discountTotalList;
	
	@OneToMany(mappedBy = "customer")
	List<CustomerDiscountProductPercent> discountProductPercentList;
	
	@OneToMany(mappedBy = "customer")
	List <CustomerDiscountProductQuantity> discountProductQuantityList;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<CustomerDiscountTotal> getDiscountTotalList() {
		return discountTotalList;
	}

	public void setDiscountTotalList(List<CustomerDiscountTotal> discountTotalList) {
		this.discountTotalList = discountTotalList;
	}

	public List<CustomerDiscountProductPercent> getDiscountProductPercentList() {
		return discountProductPercentList;
	}

	public void setDiscountProductPercentList(List<CustomerDiscountProductPercent> discountProductPercentList) {
		this.discountProductPercentList = discountProductPercentList;
	}

	public List<CustomerDiscountProductQuantity> getDiscountProductQuantityList() {
		return discountProductQuantityList;
	}

	public void setDiscountProductQuantityList(List<CustomerDiscountProductQuantity> discountProductQuantityList) {
		this.discountProductQuantityList = discountProductQuantityList;
	}
	

}
