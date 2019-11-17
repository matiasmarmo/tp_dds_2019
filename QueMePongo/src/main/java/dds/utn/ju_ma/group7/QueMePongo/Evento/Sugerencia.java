package dds.utn.ju_ma.group7.QueMePongo.Evento;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;

@Entity
public class Sugerencia {
	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Atuendo atuendo;
	@Enumerated(EnumType.STRING)
	private EstadoSugerencia estado;
	
	private Long calificacion;

	public Long getId() {
		return id;
	}

	public Sugerencia() {
	}
	
	public boolean esDichaSugerencia(Long unId) {
		return id == unId;
	}

	public Sugerencia(Atuendo atuendo) {
		this.atuendo = atuendo;
		this.estado = EstadoSugerencia.PENDIENTE;
	}

	public List<Prenda> todasLasPrendas() {
		return this.atuendo.todasLasPrendas();
	}

	public Atuendo getAtuendo() {
		return atuendo;
	}

	public EstadoSugerencia getEstado() {
		return estado;
	}

	public boolean fueAceptada() {
		return this.esDeEstado(EstadoSugerencia.ACEPTADA);
	}
	
	public boolean estaPendiente() {
		return this.esDeEstado(EstadoSugerencia.PENDIENTE);
	}

	private void setEstado(EstadoSugerencia estado) {
		this.estado = estado;
	}

	public void aceptar() {
		this.setEstado(EstadoSugerencia.ACEPTADA);
	}

	public void rechazar() {
		this.setEstado(EstadoSugerencia.RECHAZADA);
	}
	
	public boolean fueCalificada() {
		return this.calificacion != null;
	}
	
	public void calificar(Long calificacionUsuario) {
		if(!this.fueCalificada()) {
			this.setEstado(EstadoSugerencia.CALIFICADA);
		}
		this.calificacion = calificacionUsuario;
	}

	public void deshacerOperacion() {
		this.setEstado(EstadoSugerencia.PENDIENTE);
	}

	public boolean esDeEstado(EstadoSugerencia estado) {
		return this.estado == estado;
	}
	
	public void rechazarSiEsPendiente() {
		if (this.esDeEstado(EstadoSugerencia.PENDIENTE)) {
			this.rechazar();
		}
	}

	public Long getCalificacion() {
		return this.calificacion;
	}
	
}
