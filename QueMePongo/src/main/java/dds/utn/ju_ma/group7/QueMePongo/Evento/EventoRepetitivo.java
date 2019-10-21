package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

@Entity
public class EventoRepetitivo extends Evento {

	@Basic
	@Temporal(TemporalType.DATE)
	private Calendar fechaUltimaInstancia;
	@Enumerated(EnumType.STRING)
	private TipoRecurrencia tipoRecurrencia;
	@Transient
	private RepositorioEventos repositorioEventos;

	public EventoRepetitivo() {
	}

	public EventoRepetitivo(Usuario usuario, Guardarropa guardarropa, String descripcion, Calendar fechaInicio,
			TipoRecurrencia tipoRecurrencia, RepositorioEventos repositorio) {
		this.usuario = usuario;
		this.guardarropa = guardarropa;
		this.descripcion = descripcion;
		this.fechaUltimaInstancia = fechaInicio;
		this.tipoRecurrencia = tipoRecurrencia;
		this.repositorioEventos = repositorio;
	}

	private Calendar fechaSiguienteInstancia() {
		return this.tipoRecurrencia.obtenerFechaSiguienteIntancia(this.fechaUltimaInstancia);
	}

	@Override
	public boolean esProximo(Calendar unaFecha) {
		return this.diasEntreFechas(unaFecha, this.fechaSiguienteInstancia()) < 5;
	}

	@Override
	public boolean fueSugerido(Calendar fechaActual) {
		return false;
	}

	@Override
	public void serSugerido(List<Sugerencia> sugerencias) {
		Calendar fechaSiguiente = this.fechaSiguienteInstancia();
		EventoUnico instancia = this.repositorioEventos.instanciarEventoUnico(this.usuario, this.guardarropa,
				fechaSiguiente, this.descripcion);
		instancia.serSugerido(sugerencias);
		this.fechaUltimaInstancia = fechaSiguiente;
	}

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

	public List<Sugerencia> getSugerenciasPendientes(Calendar fechaReferencia) {
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
	public boolean esEnFecha(Calendar fecha) {
		return this.fechaSiguienteInstancia().compareTo(fecha) == 0;
	}

	@Override
	public Sugerencia getSugerenciaPorId(Long id) {
		return new Sugerencia();
	}
	
}
