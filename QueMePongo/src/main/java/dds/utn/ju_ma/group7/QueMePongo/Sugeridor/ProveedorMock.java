package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;

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

	public double getTemperatura(Calendar fecha) {
		return temperatura;
	}

	public boolean hayTormentas(Calendar fecha) {
		return hayTormentas;
	}

	public boolean hayNieve(Calendar fecha) {
		return hayNieve;
	}

	public boolean hayLluvia(Calendar fecha) {
		return hayLluvia;
	}

	public boolean hayClimaSoleado(Calendar fecha) {
		return hayClimaSoleado;
	}

	public boolean hayClimaVentoso(Calendar fecha) {
		return hayClimaVentoso;
	}
}
