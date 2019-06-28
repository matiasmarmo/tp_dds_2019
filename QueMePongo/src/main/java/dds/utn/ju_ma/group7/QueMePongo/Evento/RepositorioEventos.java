package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class RepositorioEventos {

	private static List<EventoUnico> eventos = new ArrayList<>();

	public RepositorioEventos() {
	}

	public EventoUnico instanciarEvento(Usuario usuario, Guardarropa guardarropas, Calendar fecha, String descripcion) {
		EventoUnico evento = new EventoUnico(usuario, guardarropas, fecha, descripcion);
		RepositorioEventos.eventos.add(evento);
		return evento;
	}

	public List<EventoUnico> eventosProximos(Calendar fecha) {
		return RepositorioEventos.eventos.stream().filter(evento -> evento.esProximo(fecha) && !evento.fueSugerido(fecha))
				.collect(Collectors.toList());
	}

	public List<EventoUnico> eventosDelUsuario(Usuario usuario) {
		return eventos.stream().filter(evento -> evento.esDeUsuario(usuario)).collect(Collectors.toList());
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
}