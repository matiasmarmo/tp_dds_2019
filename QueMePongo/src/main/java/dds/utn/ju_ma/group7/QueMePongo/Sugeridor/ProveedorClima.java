package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;

import javax.json.JsonObject;

public interface ProveedorClima {
	
	public JsonObject getClima(Calendar fecha);

	public double getTemperatura(JsonObject clima);
	
	public boolean hayTormentas(JsonObject clima, Calendar fecha);
	
	public boolean hayNieve(JsonObject clima, Calendar fecha);
	
	public boolean hayLluvia(JsonObject clima, Calendar fecha);
	
	public boolean hayClimaSoleado(JsonObject clima, Calendar fecha);
	
	public boolean hayClimaVentoso(JsonObject clima, Calendar fecha);
	
}
