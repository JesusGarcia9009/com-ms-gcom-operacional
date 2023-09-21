package com.gcom.operacional.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryTweaksDto {
	
	private Long   id;
	private String username;
	private Date dateInventoryTweaks;
	private String reason;
	private String inventoryTweaksState;
	private String additionalInformation;
	
	private List<InventoryTweaksProductDto> inventoryTweaksProductsList = new ArrayList<>();

	public InventoryTweaksDto(Long id, String username, Date dateInventoryTweaks, String reason,
			String inventoryTweaksState, String additionalInformation) {
		super();
		this.id = id;
		this.username = username;
		this.dateInventoryTweaks = dateInventoryTweaks;
		this.reason = reason;
		this.inventoryTweaksState = inventoryTweaksState;
		this.additionalInformation = additionalInformation;
	}


	
}
