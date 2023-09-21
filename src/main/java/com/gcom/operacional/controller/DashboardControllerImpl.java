package com.gcom.operacional.controller;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcom.operacional.dto.DashboardDataDto;
import com.gcom.operacional.service.DashboardService;
import com.gcom.operacional.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${gcom.base-uri}/dashboard")
public class DashboardControllerImpl implements DashboardController {
	
	/**
	 * Global variables
	 */
	private final DashboardService dashboardService;
	
	/**
     * Class constructor with @autowire annotation
     * 
     * @param UsersService @see {@link UsersService}
     */
    public DashboardControllerImpl(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

	@Override
	@GetMapping("/widget")
	public ResponseEntity<DashboardDataDto> getDashboardWidgetData() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		DashboardDataDto response = dashboardService.getDashboardWidgetData();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return ResponseEntity.ok(response);
	}


	
	
}
