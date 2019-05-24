package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;

public abstract class Usuario {
	
	private List<Guardarropa> guardarropas;
	
	public Usuario() {
		this.guardarropas = new ArrayList<>();
	}	

	public void agregarGuardarropa(List<Prenda> prendas) {
		this.guardarropas.add(this.instanciarGuardarropas(prendas));
	}
	
	protected abstract Guardarropa instanciarGuardarropas(List<Prenda> prendas);

	public List<Atuendo> obtenerAtuendos() {
		return this.guardarropas
				.stream()
				.flatMap( unGuardarropa -> unGuardarropa.generarStreamDeAtuendos() )
				.collect(Collectors.toList());
	}

}
