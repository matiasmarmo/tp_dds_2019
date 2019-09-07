package dds.utn.ju_ma.group7.QueMePongo.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.annotations.Observable;

import dds.utn.ju_ma.group7.QueMePongo.Evento.EventoUnico;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventos;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventosPersistente;

@Observable
public class EventosViewModel {
	private List<EventoObservable> eventos;
	private String fechaInicio;
	private String fechaFin;
	private RepositorioEventos repositorioEventos;
	
	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public EventosViewModel() {
		this.eventos = new ArrayList<EventoObservable>();
		this.repositorioEventos = new RepositorioEventosPersistente();
	}

	public List<EventoObservable> getA() {
		return eventos;
	}

	public void setA(List<EventoObservable> eventos) {
		this.eventos = eventos;
	}
	
	public void cargarEventos() {
		List<EventoUnico> eventos = Arrays.asList();
		List<EventoObservable> observables = Arrays.asList();
		Calendar c1;
		Calendar c2;
		try {
			c1 = transformarStringAFecha(fechaInicio);
		}catch (Exception e){
			this.setFechaInicio("Fecha invalida");
			return;
		}
		try {
			c2 = transformarStringAFecha(fechaFin);
		}catch (Exception e){
			this.setFechaFin("Fecha invalida");
			return;
		}
		
		eventos = this.repositorioEventos.obtenerEventosEntreFechas(c1, c2);
		observables = 
				eventos.stream().map(evento -> new EventoObservable(evento))
				.collect(Collectors.toList());
		this.eventos = observables;
	}
	
	private Calendar transformarStringAFecha(String fecha) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(fecha);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;		
	}
}
