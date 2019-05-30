package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;

public class ProveedorMock implements ProveedorClima {

	private double temperatura;
	
	public ProveedorMock(double temperatura) {
		this.temperatura = temperatura;
	}
	
	public double getTemperatura(Calendar fecha) {
		return temperatura;
	}
}
