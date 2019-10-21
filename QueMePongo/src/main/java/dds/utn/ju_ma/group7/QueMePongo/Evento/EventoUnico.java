package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dds.utn.ju_ma.group7.QueMePongo.Excepciones.EventoInvalidoException;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

@Entity
public class EventoUnico extends Evento {
	@Basic
	@Temporal(TemporalType.DATE)
	private Calendar fecha;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	public List<Sugerencia> sugerencias = new ArrayList<Sugerencia>();

	public EventoUnico() {
	}

	public EventoUnico(Usuario usuario, Guardarropa guardarropa, Calendar fecha, String descripcion) {
		this.usuario = usuario;
		this.guardarropa = guardarropa;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}

	@Override
	public boolean esProximo(Calendar unaFecha) {
		return ChronoUnit.DAYS.between(unaFecha.toInstant(), this.fecha.toInstant()) < 5;
	}

	public boolean esPosteriorA(Calendar unaFecha) {
		return this.diasEntreFechas(unaFecha, this.fecha) > 0;
	}

	public boolean esAnteriorA(Calendar unaFecha) {
		return this.diasEntreFechas(unaFecha, this.fecha) <= 0;
	}

	@Override
	public Calendar getProximaFecha() {
		return fecha;
	}

	public List<Sugerencia> getSugerencias() {
		return sugerencias;
	}

	@Override
	public boolean fueSugerido(Calendar fechaReferencia) {
		return !this.sugerencias.isEmpty();
	}

	@Override
	public void serSugerido(List<Sugerencia> sugerencias) {
		if (sugerencias.isEmpty()) {
			throw new EventoInvalidoException("No hay sugerencias");
		} else {
			this.sugerencias = sugerencias;
		}
		this.usuario.notificar(this, "Tenés nuevas sugerencias!");
	}

	@Override
	public List<Sugerencia> getSugerenciasAceptadas(Calendar fechaReferencia) {
		return this.sugerencias.stream().filter(sugerencia -> sugerencia.fueAceptada()).collect(Collectors.toList());
	}

	@Override
	public List<Sugerencia> getSugerenciasPendientes(Calendar fechaReferencia) {
		return this.sugerencias.stream().filter(sugerencia -> sugerencia.estaPendiente()).collect(Collectors.toList());
	}

	@Override
	public List<EventoUnico> instanciasEntreFechas(Calendar fechaInicio, Calendar fechaFin) {
		List<EventoUnico> resultado = new ArrayList<EventoUnico>();
		if (this.esPosteriorA(fechaInicio) && this.esAnteriorA(fechaFin)) {
			resultado.add(this);
		}
		return resultado;
	}

	@Override
	public boolean esEnFecha(Calendar fecha) {
		return this.fecha.compareTo(fecha) == 0;
	}
	
	@Override
	public Sugerencia getSugerenciaPorId(Long id) {
		return this.sugerencias.stream().filter(sugerencias -> sugerencias.getId() == id).collect(Collectors.toList()).get(0);
	}
	
	@Override
	public void rechazarSugerenciasPendientes() {
		this.sugerencias.stream().forEach(sugerencias -> sugerencias.rechazarSiEsPendiente());
	}

}
