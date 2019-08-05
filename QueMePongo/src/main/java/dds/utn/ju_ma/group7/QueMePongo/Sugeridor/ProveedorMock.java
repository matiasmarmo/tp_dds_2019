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
	
	public ProveedorClima setTemperatura(double temperatura) {
		this.temperatura = temperatura;
		return this;
	}

	public ProveedorClima setHayTormentas(boolean hayTormentas) {
		this.hayTormentas = hayTormentas;
		return this;
	}

	public ProveedorClima setHayNieve(boolean hayNieve) {
		this.hayNieve = hayNieve;
		return this;
	}

	public ProveedorClima setHayLluvia(boolean hayLluvia) {
		this.hayLluvia = hayLluvia;
		return this;
	}

	public ProveedorClima setHayClimaSoleado(boolean hayClimaSoleado) {
		this.hayClimaSoleado = hayClimaSoleado;
		return this;
	}

	public ProveedorClima setHayClimaVentoso(boolean hayClimaVentoso) {
		this.hayClimaVentoso = hayClimaVentoso;
		return this;
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
