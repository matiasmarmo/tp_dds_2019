package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class RepositorioEventos {

	private static List<Evento> eventos = new ArrayList<>();
	
	public RepositorioEventos() {}

	public Evento instanciarEvento(Usuario usuario, Guardarropa guardarropas, Calendar fecha, String descripcion) {
		Evento evento = new Evento(usuario, guardarropas, fecha, descripcion);
		RepositorioEventos.eventos.add(evento);
		return evento;
	}

	public List<Evento> eventosProximos(Calendar fecha) {
		return RepositorioEventos.eventos.stream().filter(evento -> evento.esProximo(fecha) && !evento.fueSugerido())
				.collect(Collectors.toList());
	}

	public List<Evento> eventosDelUsuario(Usuario usuario) {
		return eventos.stream().filter(evento -> evento.esDeUsuario(usuario)).collect(Collectors.toList());
	}

	private Stream<Sugerencia> sugerenciasDeEventos(List<Evento> eventos) {
		return eventos.stream().map(evento -> evento.getSugerencias()).flatMap(sugerencia -> sugerencia.stream());
	}

	private Stream<Sugerencia> filtrarSugerenciasSegunEstado(Stream<Sugerencia> sugerencias, EstadoSugerencia estado) {
		return sugerencias.filter(sugerencia -> sugerencia.esDeEstado(estado));
	}

	private Stream<Sugerencia> filtrarSugerenciasAceptadas(Stream<Sugerencia> sugerencias) {
		return this.filtrarSugerenciasSegunEstado(sugerencias, EstadoSugerencia.ACEPTADA);
	}

	private Stream<Sugerencia> filtrarSugerenciasRechazadas(Stream<Sugerencia> sugerencias) {
		return this.filtrarSugerenciasSegunEstado(sugerencias, EstadoSugerencia.RECHAZADA);
	}

	public List<Sugerencia> sugerenciasAceptadasDelUsuario(Usuario usuario) {
		return this.filtrarSugerenciasAceptadas(this.sugerenciasDeEventos(this.eventosDelUsuario(usuario)))
				.collect(Collectors.toList());
	}

	public List<Sugerencia> sugerenciasRechazadasDelUsuario(Usuario usuario) {
		return this.filtrarSugerenciasRechazadas(this.sugerenciasDeEventos(this.eventosDelUsuario(usuario)))
				.collect(Collectors.toList());
	}
}