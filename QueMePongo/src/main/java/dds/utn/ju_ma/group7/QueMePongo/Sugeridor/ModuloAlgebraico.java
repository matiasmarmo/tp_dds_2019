package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;

public class ModuloAlgebraico {
	
	private ModuloAlgebraico() {};

	public static double truncarADosDecimales(double numero) {
		return Math.floor(numero * 100) / 100;
	}
	
	public static double fahrenheitToCelsius(double gradosFahrenheit) {
		return (gradosFahrenheit - 32)* 5/9;
	}

	public static double kelvinToCelsius(double gradosKelvin) {
		return gradosKelvin - 273.15;
	}
	
	public static double millasPorHoraAKilometrosPorHora(double millasPorHora) {
		return millasPorHora * 1.60934;
	}
	
	public static Calendar redondearFecha(Calendar fecha) {
		int hour = fecha.get(Calendar.HOUR_OF_DAY);
		if (hour % 3 == 1) {
			fecha.add(Calendar.HOUR_OF_DAY, 2);
		} else if (hour % 3 == 2) {
			fecha.add(Calendar.HOUR_OF_DAY, 1);
		}
		return fecha;
	}	
}
