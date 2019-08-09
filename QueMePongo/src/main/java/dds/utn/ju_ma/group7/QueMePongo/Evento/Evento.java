package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public abstract class Evento {
	
	protected String descripcion;
	protected Usuario usuario;
	protected Guardarropa guardarropa; 
	
	public boolean esDeUsuario(Usuario usuario) {
		return this.usuario == usuario;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public abstract Calendar getProximaFecha();
	
	public Usuario getUsuario() {
		return usuario;
	}

	public Guardarropa getGuardarropa() {
		return guardarropa;
	}
	
	public boolean suGuardarropasEs(Guardarropa guardarropa) {
		return this.guardarropa == guardarropa;
	}
	
	protected long diasEntreFechas(Calendar unaFecha, Calendar otraFecha) {
		return ChronoUnit.DAYS.between(unaFecha.toInstant(), otraFecha.toInstant());
	}
	
	public abstract boolean esProximo(Calendar unaFecha);
	
	public abstract boolean fueSugerido(Calendar fechaReferencia);
	
	public abstract void serSugerido(List<Sugerencia> sugerencias);

	public abstract List<EventoUnico> instanciasEntreFechas(Calendar fechaInicio, Calendar fechaFin);
	
	public abstract List<Sugerencia> getSugerenciasAceptadas(Calendar fechaReferencia);
	
	public abstract List<Sugerencia> getSugerencias();
	
	public abstract boolean esEnFecha(Calendar fecha);

}
