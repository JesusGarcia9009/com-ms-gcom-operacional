package com.gcom.operacional.entity;
// Generated 10-02-2023 09:10:59 by Hibernate Tools 4.3.6.Final

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * QuotationProduct generated by hbm2java
 */
@Entity
@Table(schema = "public", name = "quotation_product")
public class QuotationProduct implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Product product;
	private Quotation quotation;
	private BigDecimal amount;
	private BigDecimal salePrice;
	private String delivery;

	public QuotationProduct() {
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
	@JoinColumn(name = "id_product", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_quotation", nullable = false)
	public Quotation getQuotation() {
		return this.quotation;
	}

	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}

	@Column(name = "amount", nullable = false, precision = 0, scale = 0)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name = "sale_price", nullable = false, precision = 0, scale = 0)
	public BigDecimal getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	
	@Column(name = "delivery", length = 50)
	public String getDelivery() {
		return this.delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

}