package dds.utn.ju_ma.group7.QueMePongo.Ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.uqbar.commons.model.annotations.Observable;

import dds.utn.ju_ma.group7.QueMePongo.Evento.EventoUnico;

@Observable
public class EventoObservable {

	private Calendar fecha;
	private String descripcion;
	private String fueSugerido;
	
	public EventoObservable(EventoUnico evento) {
		this.fecha = evento.getProximaFecha();
		this.descripcion = evento.getDescripcion();
		this.fueSugerido = 
				evento.fueSugerido(Calendar.getInstance()) ? "Si" : "No";
	}

	public String getFecha() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(this.fecha.getTime());
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getFueSugerido() {
		return fueSugerido;
	}

}
