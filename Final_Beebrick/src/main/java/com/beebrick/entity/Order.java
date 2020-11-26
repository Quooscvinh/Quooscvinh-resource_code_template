package com.beebrick.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderID")
	private Integer orderID;
	
	@Column(name = "OrderCode")
	private String orderCode;

	@Column(name = "CreatedDate")
	private LocalDateTime createdDate;
	
	@Column(name = "Status")
	private boolean status;
	
	@Column(name = "DeliveryFee")
	private double deliveryFee;
	
	@Column(name = "DeliveryAddress")
	private String deliveryAddress;
	
	@Column(name = "NameReceiver")
	private String nameReceiver;
	
	@Column(name = "PhoneReceiver")
	private String phoneReceiver;
	
	@Column(name = "Description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "UserID")
	private User users;
	
	@ManyToOne
	@JoinColumn(name = "PaymentID")
	private Payment payment;
	
	@OneToMany(mappedBy = "OrderID")
	Set<OrderDetail> orderDetail;

	@OneToMany(mappedBy = "orders", fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Shipment> shipment;

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getNameReceiver() {
		return nameReceiver;
	}

	public void setNameReceiver(String nameReceiver) {
		this.nameReceiver = nameReceiver;
	}

	public String getPhoneReceiver() {
		return phoneReceiver;
	}

	public void setPhoneReceiver(String phoneReceiver) {
		this.phoneReceiver = phoneReceiver;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(Set<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public List<Shipment> getShipment() {
		return shipment;
	}

	public void setShipment(List<Shipment> shipment) {
		this.shipment = shipment;
	}
}
