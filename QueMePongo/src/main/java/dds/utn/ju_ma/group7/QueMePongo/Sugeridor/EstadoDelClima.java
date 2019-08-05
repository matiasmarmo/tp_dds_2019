package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;

import javax.json.JsonObject;

public class EstadoDelClima {

	private JsonObject clima;
	private ProveedorClima proveedorClima;
	private Calendar fecha;
	
	public EstadoDelClima(ProveedorClima proveedorClima, Calendar fecha) {
		this.proveedorClima = proveedorClima;
		this.clima = proveedorClima.getClima(fecha);
		this.fecha = fecha;
	}
	
	public double getTemperatura() {
		return proveedorClima.getTemperatura(clima);
	}
	
	public boolean hayTormenta() {
		return proveedorClima.hayTormentas(clima, fecha);
	}
	
	public boolean hayNieve() {
		return proveedorClima.hayNieve(clima, fecha);
	}
	
	public boolean hayLluvia() {
		return proveedorClima.hayLluvia(clima, fecha);
	}
	
	public boolean hayClimaSoleado() {
		return proveedorClima.hayClimaSoleado(clima, fecha);
	}
	
	public boolean hayClimaVentoso() {
		return proveedorClima.hayClimaVentoso(clima, fecha);
	}
	
}
