package com.beebrick.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderDetailID implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "OrderID")
	private Integer orderID;
	
	@Column(name = "ProductID")
	private Integer productID;

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
