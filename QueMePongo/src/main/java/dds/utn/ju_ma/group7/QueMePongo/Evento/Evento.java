package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.Calendar;
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
	
	public boolean esProximo(Calendar unaFecha) {
		return (unaFecha.get(Calendar.DAY_OF_MONTH) - this.fecha.get(Calendar.DAY_OF_MONTH)) < 4;
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
	
	public void serSugerido() {
		this.setSugerencias(Sugeridor.sugerir(guardarropa.generarAtuendos()));
		usuario.haySugerenciasNuevas();
	}
	
}
