package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;

public interface ProveedorClima {

	public double getTemperatura(Calendar fecha);
	
	public boolean hayTormentas(Calendar fecha);
	
	public boolean hayNieve(Calendar fecha);
	
	public boolean hayLluvia(Calendar fecha);
	
	public boolean hayClimaSoleado(Calendar fecha);
	
	public boolean hayClimaVentoso(Calendar fecha);
	
}
