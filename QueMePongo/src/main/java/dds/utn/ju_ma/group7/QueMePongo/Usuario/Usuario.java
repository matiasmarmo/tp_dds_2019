package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;

public abstract class Usuario {
	
	private Set<Guardarropa> guardarropas;
	private Sensibilidad sensibilidad;
	
	public Usuario() {
		this.guardarropas = new HashSet<Guardarropa>();
		this.sensibilidad = new Sensibilidad();
	}	

	public void agregarGuardarropa(Guardarropa guardarropa) {
		this.verificarGuardarropas(guardarropa);
		this.guardarropas.add(guardarropa);
		if(!guardarropa.usuarioTieneAcceso(this)) {
			guardarropa.agregarUsuario(this);
		}
	}
	
	public Sensibilidad getSensibilidad() {
		return this.sensibilidad;
	}
	
	public boolean tieneAccesoAGuardarropas(Guardarropa guardarropasBuscado) {
		return this.guardarropas.stream().anyMatch(unGuardarropas -> unGuardarropas == guardarropasBuscado);
	}
	
	protected abstract void verificarGuardarropas(Guardarropa guardarropa);

	public List<Atuendo> obtenerAtuendos() {
		return this.guardarropas
				.stream()
				.flatMap( unGuardarropa -> unGuardarropa.generarStreamDeAtuendos() )
				.collect(Collectors.toList());
	}
	
	public void tuvoFrio(TipoSensibilidad tipo) {
		this.sensibilidad.tuvoFrio(tipo);
	}
	
	public void tuvoCalor(TipoSensibilidad tipo) {
		this.sensibilidad.tuvoCalor(tipo);
	}

}
