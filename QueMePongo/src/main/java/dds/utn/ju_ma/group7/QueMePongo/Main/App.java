package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.Calendar;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.AccuWeatherProveedor;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.OpenWeatherMapProveedor;

public class App {
	public static void main(String[] args) {

//		TEST ALERTAS METEOROLOGICAS
		OpenWeatherMapProveedor proveedor1 = new OpenWeatherMapProveedor();
		AccuWeatherProveedor proveedor2 = new AccuWeatherProveedor();

		Calendar fecha = Calendar.getInstance();
		int anio = 2019, mes = 8, dia = 5, hora = 10, minutos = 0;
		fecha.set(anio, mes, dia, hora, minutos);

		System.out.println("Fecha: " + fecha.get(Calendar.DAY_OF_MONTH) + " del " + fecha.get(Calendar.MONTH) + " a las "
				+ fecha.get(Calendar.HOUR_OF_DAY) + "hs en Buenos Aires");
		System.out.println("\nProveedor OpenWeatherMap");
		System.out.println("Temperatura: " + proveedor1.getTemperatura(fecha) + " °C");
		System.out.println("Tormentas: " + proveedor1.hayTormentas(fecha));
		System.out.println("Nieve: " + proveedor1.hayNieve(fecha));
		System.out.println("Lluvia: " + proveedor1.hayLluvia(fecha));
		System.out.println("Soleado: " + proveedor1.hayClimaSoleado(fecha));
		System.out.println("Ventoso: " + proveedor1.hayClimaVentoso(fecha));
		
		System.out.println("\nProveedor AccuWeather");
		System.out.println("Temperatura: " + proveedor2.getTemperatura(fecha) + " °C");
		System.out.println("Tormentas: " + proveedor2.hayTormentas(fecha));
		System.out.println("Nieve: " + proveedor2.hayNieve(fecha));
		System.out.println("Lluvia: " + proveedor2.hayLluvia(fecha));
		System.out.println("Soleado: " + proveedor2.hayClimaSoleado(fecha));
		System.out.println("Ventoso: " + proveedor2.hayClimaVentoso(fecha));

//		   TEST API CLIMA CON PROVEEDORES
//		   AccuWeatherProveedor proveedor1 = new AccuWeatherProveedor();
//		   OpenWeatherMapProveedor proveedor2 = new OpenWeatherMapProveedor();
//		   
//		   Calendar fecha = Calendar.getInstance(); int anio = 2019, mes = 8, dia = 5,
//		   hora = 15, minutos = 0; fecha.set(anio, mes, dia, hora, minutos);
//		   
//		   System.out.println("Fecha: " + fecha.get(Calendar.DAY_OF_MONTH) + " " +
//		   fecha.get(Calendar.MONTH) + " " + fecha.get(Calendar.HOUR_OF_DAY) + "hs");
//		   System.out.println("Temperatura proveedor AccuWeather: " +
//		   proveedor1.getTemperatura(fecha) + " °C");
//		   System.out.println("Temperatura proveedor OpenWeatherMap: " +
//		   proveedor2.getTemperatura(fecha) + " °C");

//		   TEST API CLIMA FECHA INVALIDA
//		   AccuWeatherProveedor proveedor1 = new AccuWeatherProveedor();
//		
//		   Calendar fechaInvalida = Calendar.getInstance(); fechaInvalida.set(2048, 0,
//		   0, 0, 0); System.out.println("\nFecha: Año 2048"); try {
//		   proveedor1.getTemperatura(fechaInvalida); } catch (FechaInexistenteException
//		   e) { System.out.println(e); }
//		   
//		   try { proveedor2.getTemperatura(fechaInvalida); } catch
//		   (FechaInexistenteException e) { System.out.println(e); }

//		   TEST API CLIMA CON MOCK
//		   ProveedorMock proveedorMock = new ProveedorMock(15);
//		   System.out.println(proveedorMock.getTemperatura(Calendar.getInstance()));

	}
}
