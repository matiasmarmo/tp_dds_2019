package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.Sugeridor;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class Evento {
	private Date fecha;
	private String descripcion;
	private List<Sugerencia> sugerencias;
	private Usuario usuario;
	private Guardarropa guardarropa;
	
	public Evento(Usuario usuario, Guardarropa guardarropa, Date fecha, String descripcion) {
		this.usuario = usuario;
		this.guardarropa = guardarropa;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.sugerencias = null;
	}
	
	public boolean esProximo(Date unaFecha) {
		long diff = this.fecha.getTime() - unaFecha.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) < 4; 
	}
	
	public Date getFecha() {
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
