package com.gcom.operacional.utils;

public class NumberToWords {

	public static class ConvertidorPesos {

		private static final String[] UNIDADES = { "", "un", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho",
				"nueve" };
		private static final String[] UNIDADES_DECENA = { "", "once", "doce", "trece", "catorce", "quince", "diesiseis", "diesisiete", "diesiocho",
		"diesinueve" };
		private static final String[] DECENAS = { "", "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta",
				"ochenta", "noventa" };
		private static final String[] CENTENAS = { "", "ciento", "doscientos", "trescientos", "cuatrocientos",
				"quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos" };

		private static final String[] GRUPOS = { "", "mil", "millón", "mil millones", "billón", "mil billones",
				"trillón", "mil trillones" };

		public static String convertir(long numero) {
			if (numero == 0) {
				return "cero pesos";
			}
			if (numero < 0) {
				return "menos " + convertir(Math.abs(numero));
			}
			String palabras = "";
			int grupo = 0;
			while (numero > 0) {
				long grupoNumero = numero % 1000;
				int centenas = (int) (grupoNumero / 100);
				int decenas = (int) ((grupoNumero % 100) / 10);
				int unidades = (int) (grupoNumero % 10);
				String palabrasGrupo = "";
				if (centenas > 0) {
					palabrasGrupo += CENTENAS[centenas] + " ";
				}
				if (decenas >= 2) {
					palabrasGrupo += DECENAS[decenas] + " ";
					if (unidades > 0) {
						palabrasGrupo += " ";
					}
				} else if (decenas == 1) {
					palabrasGrupo += UNIDADES_DECENA[unidades] + " ";
				} 
				
				if (unidades > 0) {
					palabrasGrupo += UNIDADES[unidades] + " ";
				}
				if (grupo > 0 && grupoNumero > 0) {
					palabrasGrupo += GRUPOS[grupo] + " ";
				}
				palabras = palabrasGrupo + palabras;
				numero /= 1000;
				grupo++;
			}
			return palabras.trim() + " pesos";
		}

	}

}
