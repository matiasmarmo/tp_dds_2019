package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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

	public Sugerencia() {
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

	private void setEstado(EstadoSugerencia estado) {
		this.estado = estado;
	}

	public void aceptar() {
		this.setEstado(EstadoSugerencia.ACEPTADA);
	}

	public void rechazar() {
		this.setEstado(EstadoSugerencia.RECHAZADA);
	}

	public void deshacerOperacion() {
		this.setEstado(EstadoSugerencia.PENDIENTE);
	}

	public boolean esDeEstado(EstadoSugerencia estado) {
		return this.estado == estado;
	}

}
