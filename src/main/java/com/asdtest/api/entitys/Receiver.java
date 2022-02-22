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
@Table(name = "receivers")
public class Receiver {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false, length = 30)
	private String name;

	@Column(name = "adress", nullable = false, length = 50)
	private String adress;

	@Column(name = "type", nullable = false, length = 15)
	private String type;

	@JoinColumn(name = "fk_city", nullable = true)
	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private City city;

	public Receiver() {

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String address) {
		this.adress = address;
	}

	@Override
	public String toString() {
		return "Area [id=" + id + ", name=" + name + ", address=" + adress + "]";
	}

}
