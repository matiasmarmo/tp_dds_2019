package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;
import dds.utn.ju_ma.group7.QueMePongo.db.WithDbAccess;

public class RepositorioEventos implements WithDbAccess {

	private static RepositorioEventos instance;

	private RepositorioEventos() {
	}

	public static RepositorioEventos getInstance() {
		if (instance == null) {
			instance = new RepositorioEventos();
		}
		return instance;
	}

	public EventoUnico instanciarEventoUnico(Usuario usuario, Guardarropa guardarropas, Calendar fecha,
			String descripcion) {
		EventoUnico evento = new EventoUnico(usuario, guardarropas, fecha, descripcion);
		this.persist(evento);
		return evento;
	}

	public EventoRepetitivo instanciarEventoRepetitivo(Usuario usuario, Guardarropa guardarropa, Calendar inicio,
			String descripcion, TipoRecurrencia recurrencia) {
		EventoRepetitivo eventoRepetitivo = new EventoRepetitivo(usuario, guardarropa, descripcion, inicio,
				recurrencia);
		this.persist(eventoRepetitivo);
		return eventoRepetitivo;
	}
	
	private List<Evento> todosLosEventos() {
		return this.entityManager().createQuery("from Evento", Evento.class).getResultList();
	}

	public List<Evento> eventosProximos(Calendar fecha) {
		return this.todosLosEventos().stream().filter(evento -> evento.esProximo(fecha) && !evento.fueSugerido(fecha))
				.collect(Collectors.toList());
	}

	public List<Evento> eventosDelUsuario(Usuario usuario) {
		return this.todosLosEventos().stream().filter(evento -> evento.esDeUsuario(usuario)).collect(Collectors.toList());
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

	public List<Evento> obtenerEventosSugeridosDeUnGuardarropasParaFecha(Guardarropa guardarropa,
			Calendar fechaReferencia) {
		return this.todosLosEventos().stream().filter(evento -> evento.esEnFecha(fechaReferencia)
				&& evento.fueSugerido(fechaReferencia) && evento.suGuardarropasEs(guardarropa))
				.collect(Collectors.toList());
	}

	public List<EventoUnico> obtenerEventosEntreFechas(Calendar fechaInicio, Calendar fechaFin) {
		return this.todosLosEventos().stream().flatMap(evento -> evento.instanciasEntreFechas(fechaInicio, fechaFin).stream())
				.collect(Collectors.toList());
	}
}