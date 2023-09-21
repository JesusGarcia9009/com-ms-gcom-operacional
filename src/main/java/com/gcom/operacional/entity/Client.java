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
 * Client generated by hbm2java
 */
@Entity
@Table(schema = "public", name = "client")
public class Client implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Contact contact;
	private DeliveryType deliveryType;
	private PaymentMethod paymentMethod;
	private ProvinceOrState provinceOrState;
	private String rutOrId;
	private String fantasyName;
	private String businessName;
	private String address;
	private String transport;
	private String deliveryObservation;
	private String attachedDocument;
	private Set<OrderNote> orderNotes = new HashSet<OrderNote>(0);
	private Set<Quotation> quotations = new HashSet<Quotation>(0);

	public Client() {
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
	@JoinColumn(name = "id_contact", nullable = false)
	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_delivery_type", nullable = false)
	public DeliveryType getDeliveryType() {
		return this.deliveryType;
	}

	public void setDeliveryType(DeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_payment_method", nullable = false)
	public PaymentMethod getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_province_or_state", nullable = false)
	public ProvinceOrState getProvinceOrState() {
		return this.provinceOrState;
	}

	public void setProvinceOrState(ProvinceOrState provinceOrState) {
		this.provinceOrState = provinceOrState;
	}

	@Column(name = "rut_or_id", nullable = false, length = 30)
	public String getRutOrId() {
		return this.rutOrId;
	}

	public void setRutOrId(String rutOrId) {
		this.rutOrId = rutOrId;
	}

	@Column(name = "fantasy_name", nullable = false, length = 50)
	public String getFantasyName() {
		return this.fantasyName;
	}

	public void setFantasyName(String fantasyName) {
		this.fantasyName = fantasyName;
	}

	@Column(name = "business_name", nullable = false, length = 50)
	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "transport", length = 500)
	public String getTransport() {
		return this.transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	@Column(name = "delivery_observation", length = 1000)
	public String getDeliveryObservation() {
		return this.deliveryObservation;
	}

	public void setDeliveryObservation(String deliveryObservation) {
		this.deliveryObservation = deliveryObservation;
	}

	@Column(name = "attached_document", length = 1000)
	public String getAttachedDocument() {
		return this.attachedDocument;
	}

	public void setAttachedDocument(String attachedDocument) {
		this.attachedDocument = attachedDocument;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	public Set<OrderNote> getOrderNotes() {
		return this.orderNotes;
	}

	public void setOrderNotes(Set<OrderNote> orderNotes) {
		this.orderNotes = orderNotes;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	public Set<Quotation> getQuotations() {
		return this.quotations;
	}

	public void setQuotations(Set<Quotation> quotations) {
		this.quotations = quotations;
	}

}
