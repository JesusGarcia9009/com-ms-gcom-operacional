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
 * OrderNoteProduct generated by hbm2java
 */
@Entity
@Table(schema = "public", name = "order_note_product")
public class OrderNoteProduct implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private OrderNote orderNote;
	private Product product;
	private BigDecimal amount;
	private BigDecimal salePrice;

	public OrderNoteProduct() {
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
	@JoinColumn(name = "id_order_note", nullable = false)
	public OrderNote getOrderNote() {
		return this.orderNote;
	}

	public void setOrderNote(OrderNote orderNote) {
		this.orderNote = orderNote;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_product", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

}
