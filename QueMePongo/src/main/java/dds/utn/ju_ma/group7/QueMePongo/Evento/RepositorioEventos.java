package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.Calendar;
import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public abstract class RepositorioEventos {
	
	protected abstract void almacenar(Evento evento);

	public EventoUnico instanciarEventoUnico(Usuario usuario, Guardarropa guardarropas, Calendar fecha,
			String descripcion) {
		EventoUnico evento = new EventoUnico(usuario, guardarropas, fecha, descripcion);
		this.almacenar(evento);
		return evento;
	}

	public EventoRepetitivo instanciarEventoRepetitivo(Usuario usuario, Guardarropa guardarropa, Calendar inicio,
			String descripcion, TipoRecurrencia recurrencia) {
		EventoRepetitivo eventoRepetitivo = new EventoRepetitivo(usuario, guardarropa, descripcion, inicio,
				recurrencia, this);
		this.almacenar(eventoRepetitivo);
		return eventoRepetitivo;
	}
	
	public abstract List<Evento> eventosProximos(Calendar fecha);

	public abstract List<Evento> eventosDelUsuario(Usuario usuario);

	public abstract List<Sugerencia> sugerenciasAceptadasDelUsuario(Usuario usuario);
	
	public abstract List<Sugerencia> obtenerSugerenciaDelUsuario(Usuario usuario, Long idSugerencia);

	public abstract List<Sugerencia> sugerenciasRechazadasDelUsuario(Usuario usuario);

	public abstract List<Evento> obtenerEventosSugeridosDeUnGuardarropasParaFecha(Guardarropa guardarropa,
			Calendar fechaReferencia);

	public abstract List<EventoUnico> obtenerEventosEntreFechas(Calendar fechaInicio, Calendar fechaFin);
	
}