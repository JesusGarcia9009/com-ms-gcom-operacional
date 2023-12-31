package com.gcom.operacional.entity;
// Generated 25-05-2023 08:37:03 by Hibernate Tools 4.3.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * QuotationDelivery generated by hbm2java
 */
@Entity
@Table(schema = "public", name = "quotation_delivery")
public class QuotationDelivery implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String description;

	public QuotationDelivery() {
	}

	public QuotationDelivery(Long id, String description) {
		this.id = id;
		this.description = description;
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

	@Column(name = "description", nullable = false, length = 50)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
