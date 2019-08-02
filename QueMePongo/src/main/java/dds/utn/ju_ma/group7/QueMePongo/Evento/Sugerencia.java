package dds.utn.ju_ma.group7.QueMePongo.Evento;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;

public class Sugerencia {
	private final Atuendo atuendo;
	private EstadoSugerencia estado;
	
	public Sugerencia(Atuendo atuendo) {
		this.atuendo = atuendo;
		this.estado = EstadoSugerencia.PENDIENTE;
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
