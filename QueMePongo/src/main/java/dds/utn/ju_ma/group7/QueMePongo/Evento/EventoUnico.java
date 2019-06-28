package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Excepciones.EventoInvalidoException;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class EventoUnico implements Evento {
	private Calendar fecha;
	private String descripcion;
	private List<Sugerencia> sugerencias = new ArrayList<Sugerencia>();
	private Usuario usuario;
	private Guardarropa guardarropa;

	public EventoUnico(Usuario usuario, Guardarropa guardarropa, Calendar fecha, String descripcion) {
		this.usuario = usuario;
		this.guardarropa = guardarropa;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}

	public boolean esProximo(Calendar unaFecha) {

		if (unaFecha.after(fecha)) {
			throw new EventoInvalidoException("La fecha introducida ha caducado");
		}

		return ChronoUnit.DAYS.between(unaFecha.toInstant(), this.fecha.toInstant()) < 5;
	}
	
	public boolean esPosteriorA(Calendar unaFecha) {
		return ChronoUnit.DAYS.between(unaFecha.toInstant(), this.fecha.toInstant()) > 0;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public List<Sugerencia> getSugerencias() {
		return sugerencias;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Guardarropa getGuardarropa() {
		return guardarropa;
	}

	public boolean fueSugerido(Calendar fechaReferencia) {
		return !this.sugerencias.isEmpty();
	}

	public void serSugerido(List<Sugerencia> sugerencias) {
		if (sugerencias.isEmpty()) {
			throw new EventoInvalidoException("No hay sugerencias");
		} else {
			this.sugerencias = sugerencias;
		}
	}

	public boolean esDeUsuario(Usuario usuario) {
		return this.usuario == usuario;
	}

}
