package com.asdtest.api.entitys;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fixed_assets")
public class FixedAsset {

	@Id
	@Column(name = "inventory_number")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long inventoryNumber;

	@Column(name = "name", nullable = false, length = 30)
	private String name;

	@Column(name = "description", nullable = false, length = 255)
	private String description;

	@Column(name = "type", nullable = false, length = 15)
	private String type;

	@Column(name = "serial", nullable = false, length = 20)
	private String serial;

	@Column(name = "weight", nullable = false, precision = 5)
	private double weight;

	@Column(name = "height", nullable = false, precision = 5)
	private double height;

	@Column(name = "width", nullable = false, precision = 5)
	private double width;

	@Column(name = "length", nullable = false, precision = 5)
	private double length;

	@Column(name = "purchase_value", nullable = false, precision = 5)
	private double purchaseValue;

	@Column(name = "date_of_purchase", nullable = false, length = 10)
	private String dateOfPurchase;

	@JoinColumn(name = "fk_receiver", nullable = true)
	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Receiver receiver;

	public FixedAsset() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public long getInventoryNumber() {
		return inventoryNumber;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getPurchaseValue() {
		return purchaseValue;
	}

	public void setPurchaseValue(double purchaseValue) {
		this.purchaseValue = purchaseValue;
	}

	public String getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "FixedAsset [inventoryNumber=" + inventoryNumber + ", name=" + name + ", description=" + description
				+ ", type=" + type + ", serial=" + serial + ", weight=" + weight + ", height=" + height + ", width="
				+ width + ", length=" + length + ", purchaseValue=" + purchaseValue + ", dateOfPurchase="
				+ dateOfPurchase + ", receiver=" + receiver + "]";
	}

}
