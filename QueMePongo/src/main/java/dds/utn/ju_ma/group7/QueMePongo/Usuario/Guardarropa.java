package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Sets;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.CategoriaPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;

public class Guardarropa {

	private List<Prenda> prendas;

	public Guardarropa() {
		this.prendas = new ArrayList<>();
	}

	public void agregarPrenda(Prenda prenda) {
		this.prendas.add(prenda);
	}

	private Set<Prenda> filtrarPrendasPorCategoria(CategoriaPrenda categoria) {
		return this.prendas.stream().filter(prenda -> prenda.esDeCategoria(categoria)).collect(Collectors.toSet());
	}
	
	public Stream<Atuendo> generarStreamDeAtuendos() {
		Set<Prenda> prendasSuperiores = this.filtrarPrendasPorCategoria(CategoriaPrenda.SUPERIOR);
		Set<Prenda> prendasInferiores = this.filtrarPrendasPorCategoria(CategoriaPrenda.INFERIOR);
		Set<Prenda> calzados = this.filtrarPrendasPorCategoria(CategoriaPrenda.CALZADO);
		Set<Prenda> accesorios = this.filtrarPrendasPorCategoria(CategoriaPrenda.ACCESORIO);
		Set<List<Prenda>> combinaciones = Sets
				.cartesianProduct(Arrays.asList(prendasSuperiores, prendasInferiores, calzados, accesorios));
		return combinaciones.stream().map(combinacion -> new Atuendo(combinacion.get(0), combinacion.get(1),
				combinacion.get(2), combinacion.get(3)));
	}

	public List<Atuendo> generarAtuendos() {
		return this.generarStreamDeAtuendos().collect(Collectors.toList());
	}
}
