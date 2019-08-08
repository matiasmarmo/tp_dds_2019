package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class RepositorioEventos {

	private List<Evento> eventos = new ArrayList<>();

	private static RepositorioEventos instance;

	private RepositorioEventos() { }
	
	public static RepositorioEventos getInstance() {
		if(instance == null) {
			instance = new RepositorioEventos();
		}
		return instance;
	}

	public void vaciar() {
		this.eventos = new ArrayList<Evento>();
	}

	public EventoUnico instanciarEventoUnico(Usuario usuario, Guardarropa guardarropas, Calendar fecha,
			String descripcion) {
		EventoUnico evento = new EventoUnico(usuario, guardarropas, fecha, descripcion);
		this.eventos.add(evento);
		return evento;
	}

	public EventoRepetitivo instanciarEventoRepetitivo(Usuario usuario, Guardarropa guardarropa, Calendar inicio,
			String descripcion, TipoRecurrencia recurrencia) {
		EventoRepetitivo eventoRepetitivo = new EventoRepetitivo(usuario, guardarropa, descripcion, inicio,
				recurrencia);
		this.eventos.add(eventoRepetitivo);
		return eventoRepetitivo;
	}

	public List<Evento> eventosProximos(Calendar fecha) {
		return this.eventos.stream()
				.filter(evento -> evento.esProximo(fecha) && !evento.fueSugerido(fecha)).collect(Collectors.toList());
	}

	public List<Evento> eventosDelUsuario(Usuario usuario) {
		return this.eventos.stream().filter(evento -> evento.esDeUsuario(usuario)).collect(Collectors.toList());
	}

	public List<Sugerencia> sugerenciasAceptadasDelUsuario(Usuario usuario) {
		return this.eventosDelUsuario(usuario).stream().map(evento -> evento.getSugerencias())
				.flatMap(sugerencia -> sugerencia.stream())
				.filter(sugerencia -> sugerencia.esDeEstado(EstadoSugerencia.ACEPTADA)).collect(Collectors.toList());
	}

	public List<Sugerencia> sugerenciasRechazadasDelUsuario(Usuario usuario) {
		return this.eventosDelUsuario(usuario).stream().map(evento -> evento.getSugerencias())
				.flatMap(sugerencia -> sugerencia.stream())
				.filter(sugerencia -> sugerencia.esDeEstado(EstadoSugerencia.RECHAZADA)).collect(Collectors.toList());
	}

	public List<Prenda> obtenerPrendasEnUso(Guardarropa guardarropa, Calendar fechaReferencia) {
		return this.eventos.stream()
				.filter(evento -> evento.esProximo(fechaReferencia) && evento.fueSugerido(fechaReferencia)
						&& evento.getGuardarropa().esElGuardarropa(guardarropa))
				
				.flatMap(evento -> evento.getSugerencias().stream()).filter(sugerencia -> sugerencia.fueAceptada())
				.flatMap(sugerencia -> sugerencia.getAtuendo().todasLasPrendas().stream()).collect(Collectors.toList());
	}

	public List<EventoUnico> obtenerEventosEntreFechas(Calendar fechaInicio, Calendar fechaFin) {
		return this.eventos.stream()
				.flatMap(evento -> evento.instanciasEntreFechas(fechaInicio, fechaFin).stream())
				.collect(Collectors.toList());
	}
}