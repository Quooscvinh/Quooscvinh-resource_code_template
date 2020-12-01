package com.beebrick.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "orderDetail")
public class OrderDetail {
	
	@EmbeddedId
	private OrderDetailID orderDetailID;
	
	@ManyToOne
	@MapsId(value = "OrderID")
	@JoinColumn(name = "OrderID")
	private Order OrderID;
	
	@ManyToOne
	@MapsId(value = "ProductID")
	@JoinColumn(name = "ProductID")
	private Product ProductID;
	
	@Column(name = "Quantity")
	private Integer quantity;
	
	@Column(name = "Price")
	private double price;
	
	@Column(name = "SubTotal")
	private double subTotal;

	public OrderDetailID getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(OrderDetailID orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	public Order getOrderID() {
		return OrderID;
	}

	public void setOrderID(Order orderID) {
		OrderID = orderID;
	}

	public Product getProductID() {
		return ProductID;
	}

	public void setProductID(Product productID) {
		ProductID = productID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
}
