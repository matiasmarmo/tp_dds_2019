package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;

public abstract class ModuloAlgebraico {

	public static double truncarADosDecimales(double numero) {
		return Math.floor(numero * 100) / 100;
	}
	
	public static double fahrenheitToCelsius(double gradosFahrenheit) {
		return (gradosFahrenheit - 32)* 5/9;
	}
	
	public static Calendar redondearFecha(Calendar fecha) {
		int hour = fecha.get(Calendar.HOUR_OF_DAY);
		int day = fecha.get(Calendar.DAY_OF_MONTH);
		if (hour == 24) {
			fecha.set(Calendar.HOUR_OF_DAY, 0);
			fecha.set(Calendar.DAY_OF_MONTH, day + 1);
		} else if (hour == 23 || hour == 22) {
			fecha.set(Calendar.HOUR_OF_DAY, 21);
		} else if (hour % 3 == 1) {
			fecha.set(Calendar.HOUR_OF_DAY, hour + 2);
		} else if (hour % 3 == 2) {
			fecha.set(Calendar.HOUR_OF_DAY, hour + 1);
		}
		return fecha;
	}
	
}
