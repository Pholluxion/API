package com.asdtest.api.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "citys")
public class City {

	@Id
	@Column(name = "name", nullable = false, length = 30)
	private String name;

	@Column(name = "zip_code", nullable = false, length = 15)
	private String zipCode;

	public City() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "City [ name=" + name + ", zipCode=" + zipCode + "]";
	}

}
