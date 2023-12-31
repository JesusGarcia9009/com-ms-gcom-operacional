package com.gcom.operacional.entity;
// Generated 10-02-2023 09:10:59 by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;

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

/**
 * Model generated by hbm2java
 */
@Entity
@Table(schema = "public", name = "model")
public class Model implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Brand brand;
	private String code;
	private String description;
	private String measure;
	private String vehicleType;
	private String approximateYear;
	private String engineDescription;
	private String typeOfMotor;
	private String notes;
	private String mast;
	private Set<Product> products = new HashSet<Product>(0);

	public Model() {
	}

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_brand", nullable = false)
	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Column(name = "code", nullable = false, length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "description", nullable = false, length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "measure", length = 100)
	public String getMeasure() {
		return this.measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	@Column(name = "vehicle_type", nullable = false, length = 100)
	public String getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Column(name = "approximate_year", length = 20)
	public String getApproximateYear() {
		return this.approximateYear;
	}

	public void setApproximateYear(String approximateYear) {
		this.approximateYear = approximateYear;
	}

	@Column(name = "engine_description", length = 50)
	public String getEngineDescription() {
		return this.engineDescription;
	}

	public void setEngineDescription(String engineDescription) {
		this.engineDescription = engineDescription;
	}

	@Column(name = "type_of_motor", length = 50)
	public String getTypeOfMotor() {
		return this.typeOfMotor;
	}

	public void setTypeOfMotor(String typeOfMotor) {
		this.typeOfMotor = typeOfMotor;
	}

	@Column(name = "notes", length = 100)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "mast", length = 100)
	public String getMast() {
		return this.mast;
	}

	public void setMast(String mast) {
		this.mast = mast;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "model")
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
