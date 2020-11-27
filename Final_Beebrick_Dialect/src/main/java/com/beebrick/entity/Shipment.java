package com.beebrick.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shipment")
public class Shipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ShipmentID")
	private Integer ShipmentID;
	
	@Column(name = "ShipmentAddress")
	private String shipmentAddress;
	
	@Column(name = "ShipmentDate")
	private LocalDateTime shipmentDate;
	
	@Column(name = "Note")
	private String note;
	
	@Column(name = "ShipmentContact")
	private String shipmentContact;
	
	@ManyToOne
	@JoinColumn(name = "OrderID")
	private Order orders;

	public Integer getShipmentID() {
		return ShipmentID;
	}

	public void setShipmentID(Integer shipmentID) {
		ShipmentID = shipmentID;
	}

	public String getShipmentAddress() {
		return shipmentAddress;
	}

	public void setShipmentAddress(String shipmentAddress) {
		this.shipmentAddress = shipmentAddress;
	}

	public LocalDateTime getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(LocalDateTime shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getShipmentContact() {
		return shipmentContact;
	}

	public void setShipmentContact(String shipmentContact) {
		this.shipmentContact = shipmentContact;
	}

	public Order getOrders() {
		return orders;
	}

	public void setOrders(Order orders) {
		this.orders = orders;
	}
}
