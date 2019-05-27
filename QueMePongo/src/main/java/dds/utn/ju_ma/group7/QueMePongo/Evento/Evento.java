package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.Sugeridor;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class Evento {
	private Calendar fecha;
	private String descripcion;
	private List<Sugerencia> sugerencias;
	private Usuario usuario;
	private Guardarropa guardarropa;

	public Evento(Usuario usuario, Guardarropa guardarropa, Calendar fecha, String descripcion) {
		this.usuario = usuario;
		this.guardarropa = guardarropa;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.sugerencias = null;
	}

	private int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	public boolean esProximo(Calendar unaFecha) {

		if (unaFecha.after(fecha)) {
			throw new EventoInvalidoException("La fecha introducida ha caducado");
		}

		return this.daysBetween(this.fecha.getTime(), unaFecha.getTime()) <= 5;
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

	public boolean fueSugerido() {
		return this.sugerencias != null;
	}

	public void setSugerencias(List<Sugerencia> sugerencias) {
		this.sugerencias = sugerencias;
	}

	public void serSugerido(Calendar fecha) {
		this.setSugerencias(Sugeridor.sugerir(guardarropa.generarAtuendos(), fecha));
		usuario.haySugerenciasNuevas();
	}

}
