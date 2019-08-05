package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.json.JsonObject;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.TipoAlerta;

public class EstadoDelClima {

	private JsonObject clima;
	private ProveedorClima proveedorClima;
	private Map<TipoAlerta, Callable<Boolean>> mapperAlertas;
	private Calendar fecha;
	
	public EstadoDelClima(ProveedorClima proveedorClima, Calendar fecha) {
		this.proveedorClima = proveedorClima;
		this.clima = proveedorClima.getClima(fecha);
		this.fecha = fecha;
		this.mapperAlertas = new HashMap<TipoAlerta, Callable<Boolean>>();
		this.mapperAlertas.put(TipoAlerta.TORMENTA, () -> this.hayTormenta());
		this.mapperAlertas.put(TipoAlerta.NIEVE, () -> this.hayNieve());
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
	
	public List<TipoAlerta> getAlertas() {
		List<TipoAlerta> alertas = new ArrayList<TipoAlerta>();
		this.mapperAlertas.entrySet().forEach(
				entry -> {
					try {
						if(entry.getValue().call()) {
							alertas.add(entry.getKey());
						}
					} catch (Exception e) {
						// No va a pasar, pero Java te obliga 
						// a atraparla
					}
				}
		);
		return alertas;
	}
	
}
