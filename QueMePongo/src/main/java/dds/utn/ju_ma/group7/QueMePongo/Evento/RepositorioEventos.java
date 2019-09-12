package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioPremium;

public abstract class RepositorioEventos {
	
	protected abstract List<Evento> todosLosEventos();
	
	protected abstract void almacenar(Evento evento);

	public EventoUnico instanciarEventoUnico(UsuarioPremium usuario, Guardarropa guardarropas, Calendar fecha,
			String descripcion) {
		EventoUnico evento = new EventoUnico(usuario, guardarropas, fecha, descripcion);
		this.almacenar(evento);
		return evento;
	}

	public EventoRepetitivo instanciarEventoRepetitivo(UsuarioPremium usuario, Guardarropa guardarropa, Calendar inicio,
			String descripcion, TipoRecurrencia recurrencia) {
		EventoRepetitivo eventoRepetitivo = new EventoRepetitivo(usuario, guardarropa, descripcion, inicio,
				recurrencia, this);
		this.almacenar(eventoRepetitivo);
		return eventoRepetitivo;
	}

	public List<Evento> eventosProximos(Calendar fecha) {
		return this.todosLosEventos().stream().filter(evento -> evento.esProximo(fecha) && !evento.fueSugerido(fecha))
				.collect(Collectors.toList());
	}

	public List<Evento> eventosDelUsuario(UsuarioPremium usuario) {
		return this.todosLosEventos().stream().filter(evento -> evento.esDeUsuario(usuario)).collect(Collectors.toList());
	}

	public List<Sugerencia> sugerenciasAceptadasDelUsuario(UsuarioPremium usuario) {
		return this.eventosDelUsuario(usuario).stream().map(evento -> evento.getSugerencias())
				.flatMap(sugerencia -> sugerencia.stream())
				.filter(sugerencia -> sugerencia.esDeEstado(EstadoSugerencia.ACEPTADA)).collect(Collectors.toList());
	}

	public List<Sugerencia> sugerenciasRechazadasDelUsuario(UsuarioPremium usuario) {
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