package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.TipoAlerta;
import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.EventoUnico;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.ParteCuerpo;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Usuario {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Set<Guardarropa> guardarropas;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Sensibilidad sensibilidad;
	public Set<Guardarropa> getGuardarropas() {
		return guardarropas;
	}

	public void setGuardarropas(Set<Guardarropa> guardarropas) {
		this.guardarropas = guardarropas;
	}

	public List<InteresEnNotificaciones> getNotificadores() {
		return notificadores;
	}

	public void setNotificadores(List<InteresEnNotificaciones> notificadores) {
		this.notificadores = notificadores;
	}

	public void setSensibilidad(Sensibilidad sensibilidad) {
		this.sensibilidad = sensibilidad;
	}

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private List<InteresEnNotificaciones> notificadores;

	public Usuario() {
	}

	public Usuario(List<InteresEnNotificaciones> notificadores) {
		this.guardarropas = new HashSet<Guardarropa>();
		this.sensibilidad = new Sensibilidad();
		this.notificadores = notificadores;
	}

	public void agregarGuardarropa(Guardarropa guardarropa) {
		this.verificarGuardarropas(guardarropa);
		this.guardarropas.add(guardarropa);
	}

	public Sensibilidad getSensibilidad() {
		return this.sensibilidad;
	}

	public boolean tieneAccesoAGuardarropas(Guardarropa guardarropasBuscado) {
		return this.guardarropas.stream().anyMatch(unGuardarropas -> unGuardarropas == guardarropasBuscado);
	}

	protected void verificarGuardarropas(Guardarropa guardarropa) {}

	public List<Atuendo> obtenerAtuendos() {
		return this.guardarropas.stream().flatMap(unGuardarropa -> unGuardarropa.generarStreamDeAtuendos())
				.collect(Collectors.toList());
	}

	public void tuvoFrio(ParteCuerpo tipo) {
		this.sensibilidad.tuvoFrio(tipo);
	}

	public void tuvoCalor(ParteCuerpo tipo) {
		this.sensibilidad.tuvoCalor(tipo);
	}

	public void notificar(EventoUnico evento, String notificacion) {
		this.notificadores.forEach(notificador -> notificador.notificar(evento, notificacion));
	}

	public void notificarAlerta(Calendar fecha, TipoAlerta alerta) {
		this.notificadores.forEach(notificador -> notificador.notificarAlerta(fecha, alerta));
	}

	public boolean esDuenioDeGuardarropas(Guardarropa guardarropa) {
		return this.guardarropas.contains(guardarropa);
	}
	
	public Long getId() {
		return this.id;
	}

}
