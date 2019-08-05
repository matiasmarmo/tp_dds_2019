package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;

public interface ProveedorClima {

	public double getTemperatura(Calendar fecha);
	
	public boolean hayTormentas(Calendar fecha);
	
	public boolean hayNieve(Calendar fecha);
	
}
