package com.gcom.operacional.service;

import com.gcom.operacional.dto.DashboardDataDto;


/**
 * UsersService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface DashboardService {

	
	/**
	 * get all Dashboard data
	 * 
	 * @param none
	 * @return model @see {@link DashboardDataDto}
	 */
	public DashboardDataDto getDashboardWidgetData();
	
}
