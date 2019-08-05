package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.TipoAlerta;
import dds.utn.ju_ma.group7.QueMePongo.Evento.EventoUnico;

public abstract class InteresEnNotificaciones {

	public abstract void notificar(EventoUnico evento, String notificacion);
	
	public abstract void notificarAlerta(Calendar fecha, TipoAlerta alerta);
	
	protected String fechaString(Calendar fecha) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(fecha.getTime()); 
	}
	
	protected String generarTextoNotificacionSugerencia(EventoUnico evento, String notificacion) {
		return "Evento: " + evento.getDescripcion() + "\n" +
				"Fecha: " + this.fechaString(evento.getProximaFecha(Calendar.getInstance())) + "\n" +
				notificacion + "\n";
	}
	
	protected String generarTextoNotificacionAlerta(Calendar fecha, TipoAlerta alerta) {
		return "ALERTA:\n" + 
				"Fecha: " + this.fechaString(fecha) + "\n" +
				alerta.getDescripcion() + "\n";
	}
}
