package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class RepositorioEventos {
	
	private static List<Evento> eventos = new ArrayList<>();
	
	public static Evento instanciarEvento(Usuario usuario, Guardarropa guardarropas, Calendar fecha, String descripcion) {
		Evento evento = new Evento(usuario, guardarropas, fecha, descripcion);
		RepositorioEventos.eventos.add(evento);
		return evento;
	}
	
	public static List<Evento> eventosProximos(Calendar fecha) {
		return RepositorioEventos.eventos.stream()
				.filter(evento -> evento.esProximo(fecha) && !evento.fueSugerido())
				.collect(Collectors.toList());
	}
}
