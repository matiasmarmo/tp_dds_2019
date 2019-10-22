package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class RepositorioEventosMock extends RepositorioEventos {
	
	public List<Evento> eventos;
	
	public RepositorioEventosMock() {
		this.eventos = new ArrayList<Evento>();
	}

	protected List<Evento> todosLosEventos() {
		return this.eventos;
	}

	@Override
	protected void almacenar(Evento evento) {
		this.eventos.add(evento);
	}
	
	@Override
	public List<Evento> eventosProximos(Calendar fecha) {
		return this.todosLosEventos().stream().filter(evento -> evento.esProximo(fecha) && !evento.fueSugerido(fecha))
				.collect(Collectors.toList());
	}

	@Override
	public List<Evento> eventosDelUsuario(Usuario usuario) {
		return this.todosLosEventos().stream().filter(evento -> evento.esDeUsuario(usuario)).collect(Collectors.toList());
	}

	@Override
	public List<Sugerencia> sugerenciasAceptadasDelUsuario(Usuario usuario) {
		return this.eventosDelUsuario(usuario).stream().map(evento -> evento.getSugerencias())
				.flatMap(sugerencia -> sugerencia.stream())
				.filter(sugerencia -> sugerencia.esDeEstado(EstadoSugerencia.ACEPTADA)).collect(Collectors.toList());
	}

	@Override
	public List<Sugerencia> obtenerSugerenciaDelUsuario(Usuario usuario, Long idSugerencia) {
		return this.eventosDelUsuario(usuario).stream().map(evento -> evento.getSugerencias())
				.flatMap(sugerencia -> sugerencia.stream())
				.filter(sugerencia -> sugerencia.esDichaSugerencia(idSugerencia)).collect(Collectors.toList());
	}

	@Override
	public List<Sugerencia> sugerenciasRechazadasDelUsuario(Usuario usuario) {
		return this.eventosDelUsuario(usuario).stream().map(evento -> evento.getSugerencias())
				.flatMap(sugerencia -> sugerencia.stream())
				.filter(sugerencia -> sugerencia.esDeEstado(EstadoSugerencia.RECHAZADA)).collect(Collectors.toList());
	}

	@Override
	public List<Evento> obtenerEventosSugeridosDeUnGuardarropasParaFecha(Guardarropa guardarropa,
			Calendar fechaReferencia) {
		return this.todosLosEventos().stream().filter(evento -> evento.esEnFecha(fechaReferencia)
				&& evento.fueSugerido(fechaReferencia) && evento.suGuardarropasEs(guardarropa))
				.collect(Collectors.toList());
	}

	@Override
	public List<EventoUnico> obtenerEventosEntreFechas(Calendar fechaInicio, Calendar fechaFin) {
		return this.todosLosEventos().stream().flatMap(evento -> evento.instanciasEntreFechas(fechaInicio, fechaFin).stream())
				.collect(Collectors.toList());
	}

}
