package com.gcom.operacional.enums;

import lombok.Getter;

@Getter
public enum OperationTypeEnum {

	NOTA_PEDIDO("NOTA_PEDIDO", "Nota de Pedido"),
	FACTURA_COMPRA("FACTURA_COMPRA", "Factura de compra"),
	AJUSTE_INVENTARIO("AJUSTE_INVENTARIO", "Ajuste de inventario");
	
	private String code;
	private String description;
	
	OperationTypeEnum(String code, String description) {
		this.code = code;
		this.description = description; 
	}

    public boolean isEqual(String code) {
        return (this.code.compareTo(code) == 0) ? true : false;
    }
	
	
}
