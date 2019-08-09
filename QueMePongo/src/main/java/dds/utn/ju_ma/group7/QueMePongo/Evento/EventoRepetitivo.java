package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class EventoRepetitivo implements Evento {

	private Usuario usuario;
	private Guardarropa guardarropa;
	private String descripcion;
	private Calendar fechaUltimaInstancia;
	private TipoRecurrencia tipoRecurrencia;

	public EventoRepetitivo(Usuario usuario, Guardarropa guardarropa, String descripcion, Calendar fechaInicio,
			TipoRecurrencia tipoRecurrencia) {
		this.usuario = usuario;
		this.guardarropa = guardarropa;
		this.descripcion = descripcion;
		this.fechaUltimaInstancia = fechaInicio;
		this.tipoRecurrencia = tipoRecurrencia;
	}

	@Override
	public Guardarropa getGuardarropa() {
		return this.guardarropa;
	}

	@Override
	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public String getDescripcion() {
		return this.descripcion;
	}

	private Calendar fechaSiguienteInstancia() {
		return this.tipoRecurrencia.obtenerFechaSiguienteIntancia(this.fechaUltimaInstancia);
	}

	@Override
	public boolean esProximo(Calendar unaFecha) {
		return ChronoUnit.DAYS.between(unaFecha.toInstant(), this.fechaSiguienteInstancia().toInstant()) < 5;
	}

	@Override
	public boolean fueSugerido(Calendar fechaActual) {
		return false;
	}

	@Override
	public void serSugerido(List<Sugerencia> sugerencias) {
		Calendar fechaSiguiente = this.fechaSiguienteInstancia();
		EventoUnico instancia = RepositorioEventos.getInstance().instanciarEventoUnico(this.usuario, this.guardarropa,
				fechaSiguiente, this.descripcion);
		instancia.serSugerido(sugerencias);
		this.fechaUltimaInstancia = fechaSiguiente;
	}

	@Override
	public boolean esDeUsuario(Usuario usuario) {
		return this.usuario == usuario;
	}

	@Override
	public List<Sugerencia> getSugerencias() {
		return new ArrayList<Sugerencia>();
	}

	@Override
	public Calendar getProximaFecha() {
		return this.fechaSiguienteInstancia();
	}

	public List<Sugerencia> getSugerenciasAceptadas(Calendar fechaReferencia) {
		return new ArrayList<Sugerencia>();
	}

	public List<EventoUnico> instanciasEntreFechas(Calendar fechaInicio, Calendar fechaFin) {
		List<Calendar> fechas = this.tipoRecurrencia.todasLasFechasEnIntervalo(this.fechaUltimaInstancia, fechaFin);
		List<EventoUnico> resultado = fechas.stream()
				.map(fecha -> new EventoUnico(this.usuario, this.guardarropa, fecha, this.descripcion))
				.collect(Collectors.toList());
		return resultado;
	}

	@Override
	public boolean suGuardarropasEs(Guardarropa guardarropa) {
		return this.guardarropa == guardarropa;
	}

	@Override
	public boolean esEnFecha(Calendar fecha) {
		return this.fechaSiguienteInstancia().compareTo(fecha) == 0;
	}
}
