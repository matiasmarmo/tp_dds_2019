package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;

public abstract class Usuario {
	
	private Set<Guardarropa> guardarropas;
	
	public Usuario() {
		this.guardarropas = new HashSet<Guardarropa>();
	}	

	public void agregarGuardarropa(Guardarropa guardarropa) {
		this.verificarGuardarropas(guardarropa);
		this.guardarropas.add(guardarropa);
		if(!guardarropa.usuarioTieneAcceso(this)) {
			guardarropa.agregarUsuario(this);
		}
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

}
