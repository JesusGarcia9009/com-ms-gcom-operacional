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
 * ProvinceOrState generated by hbm2java
 */
@Entity
@Table(schema = "public", name = "province_or_state")
public class ProvinceOrState implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private RegionOrCity regionOrCity;
	private String description;
	private Set<Provider> providers = new HashSet<Provider>(0);
	private Set<Provider> clients = new HashSet<Provider>(0);

	public ProvinceOrState() {
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
	@JoinColumn(name = "id_region_or_city", nullable = false)
	public RegionOrCity getRegionOrCity() {
		return this.regionOrCity;
	}

	public void setRegionOrCity(RegionOrCity regionOrCity) {
		this.regionOrCity = regionOrCity;
	}

	@Column(name = "description", nullable = false, length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "provinceOrState")
	public Set<Provider> getProviders() {
		return this.providers;
	}

	public void setProviders(Set<Provider> providers) {
		this.providers = providers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "provinceOrState")
	public Set<Provider> getClients() {
		return this.clients;
	}

	public void setClients(Set<Provider> clients) {
		this.clients = clients;
	}

}