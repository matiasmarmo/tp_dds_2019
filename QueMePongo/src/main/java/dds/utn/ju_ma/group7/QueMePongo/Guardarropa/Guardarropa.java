package dds.utn.ju_ma.group7.QueMePongo.Guardarropa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;

public class Guardarropa {

	protected List<Prenda> prendas;

	public Guardarropa() {
		this.prendas = new ArrayList<Prenda>();
	}

	public void agregarPrendas(List<Prenda> prendas) {
		prendas.forEach(prenda -> this.agregarPrenda(prenda));
	}

	public void agregarPrenda(Prenda prenda) {
		this.prendas.add(prenda);
	}

	public void agregarPrenda(Prenda prenda, String imagen) {
		prenda.setImagen(imagen);
		this.agregarPrenda(prenda);
	}

	public Stream<Atuendo> generarStreamDeAtuendos() {
		return GeneradorCombinaciones.generarStreamDeAtuendos(this.prendas);
	}

	public List<Atuendo> generarAtuendos() {
		return GeneradorCombinaciones.generarAtuendos(this.prendas);
	}
}
