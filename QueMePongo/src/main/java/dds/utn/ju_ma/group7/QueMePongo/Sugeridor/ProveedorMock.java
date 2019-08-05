package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;

public class ProveedorMock implements ProveedorClima {

	private double temperatura;
	private boolean hayTormentas;
	private boolean hayNieve;

	public ProveedorMock(double temperatura, boolean hayTormentas, boolean hayNieve) {
		this.temperatura = temperatura;
		this.hayTormentas = hayTormentas;
		this.hayNieve = hayNieve;
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
}
