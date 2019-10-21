package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Evento {
	@Id
	@GeneratedValue
	private Long id;
	
	protected String descripcion;
	@ManyToOne(cascade = CascadeType.PERSIST)
	protected Usuario usuario;
	@ManyToOne(cascade = CascadeType.PERSIST)
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
	
	public Long getId() {
		return id;
	}

	public abstract boolean esProximo(Calendar unaFecha);
	
	public abstract boolean fueSugerido(Calendar fechaReferencia);
	
	public abstract void serSugerido(List<Sugerencia> sugerencias);

	public abstract List<EventoUnico> instanciasEntreFechas(Calendar fechaInicio, Calendar fechaFin);
	
	public abstract List<Sugerencia> getSugerenciasAceptadas(Calendar fechaReferencia);
	
	public abstract List<Sugerencia> getSugerenciasPendientes(Calendar fechaReferencia);
	
	public abstract List<Sugerencia> getSugerencias();
	
	public abstract Sugerencia getSugerenciaPorId(Long id);
	
	public abstract boolean esEnFecha(Calendar fecha);
	
	public abstract void rechazarSugerenciasPendientes();

}
