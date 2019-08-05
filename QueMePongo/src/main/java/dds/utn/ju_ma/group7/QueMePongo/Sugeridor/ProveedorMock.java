package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;

import javax.json.JsonObject;

public class ProveedorMock implements ProveedorClima {

	private double temperatura;
	private boolean hayTormentas;
	private boolean hayNieve;
	private boolean hayLluvia;
	private boolean hayClimaSoleado;
	private boolean hayClimaVentoso;
	
	public ProveedorMock(double temperatura, boolean hayTormentas, boolean hayNieve, boolean hayLluvia,
			boolean hayClimaSoleado, boolean hayClimaVentoso) {
		this.temperatura = temperatura;
		this.hayTormentas = hayTormentas;
		this.hayNieve = hayNieve;
		this.hayLluvia = hayLluvia;
		this.hayClimaSoleado = hayClimaSoleado;
		this.hayClimaVentoso = hayClimaVentoso;
	}
	
	public JsonObject getClima(Calendar fecha) {
		return null;
	}

	public double getTemperatura(JsonObject clima) {
		return temperatura;
	}

	public boolean hayTormentas(JsonObject clima, Calendar fecha) {
		return hayTormentas;
	}

	public boolean hayNieve(JsonObject clima, Calendar fecha) {
		return hayNieve;
	}

	public boolean hayLluvia(JsonObject clima, Calendar fecha) {
		return hayLluvia;
	}

	public boolean hayClimaSoleado(JsonObject clima, Calendar fecha) {
		return hayClimaSoleado;
	}

	public boolean hayClimaVentoso(JsonObject clima, Calendar fecha) {
		return hayClimaVentoso;
	}
}
