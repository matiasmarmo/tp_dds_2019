package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;

public class ProveedorMock implements ProveedorClima {

	private double temperatura;
	private boolean hayTormentas;

	public ProveedorMock(double temperatura, boolean hayTormentas) {
		this.temperatura = temperatura;
		this.hayTormentas = hayTormentas;
	}

	public double getTemperatura(Calendar fecha) {
		return temperatura;
	}

	public boolean hayTormentas(Calendar fecha) {
		return hayTormentas;
	}
}
