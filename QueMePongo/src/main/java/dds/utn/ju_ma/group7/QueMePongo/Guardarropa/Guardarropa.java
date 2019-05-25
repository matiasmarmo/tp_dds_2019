package dds.utn.ju_ma.group7.QueMePongo.Guardarropa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.Sets;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.CategoriaPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoPrenda;

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

	private Set<Prenda> filtrarPrendasPorCategoria(CategoriaPrenda categoria) {
		return this.prendas.stream().filter(prenda -> prenda.esDeCategoria(categoria)).collect(Collectors.toSet());
	}

	private Atuendo contruirAtuendoDesdeCombinacion(List<Prenda> combinacion) {
		Prenda accesorio = combinacion.get(0);
		Prenda calzado = combinacion.get(1);
		Prenda inferior = combinacion.get(2);
		List<Prenda> prendasSuperiores = combinacion.subList(3, combinacion.size());
		return new Atuendo(prendasSuperiores, inferior, calzado, accesorio);
	}
	
	private Set<Prenda> prendasSuperioresDeJerarquia(int jerarquia) {
		return this.prendas.stream()
				.filter(prenda -> (prenda.esDeCategoria(CategoriaPrenda.SUPERIOR) && prenda.getJerarquia() == jerarquia))
				.collect(Collectors.toSet());
	}
	
	private List<Prenda> aplanarCombinacion(List<Prenda> combinacion) {
		return combinacion.stream()
				.filter(prenda -> !prenda.esPrendaNula())
				.collect(Collectors.toList());
	}
	
	private List<Set<Prenda>> generarCombinacionesDePrendasSuperiores() {
		List<Set<Prenda>> prendasDeJerarquias = new ArrayList<>();
		List<Integer> jerarquias = IntStream.rangeClosed(0, TipoPrenda.jerarquiaMaxima)
				.boxed().collect(Collectors.toList());
		jerarquias.forEach(jerarquia -> {
			Set<Prenda> prendasDeLaJerarquia = this.prendasSuperioresDeJerarquia(jerarquia);
			prendasDeLaJerarquia.add(Prenda.PrendaNula());
			prendasDeJerarquias.add(prendasDeLaJerarquia);
		});
		return prendasDeJerarquias;
	}

	public Stream<Atuendo> generarStreamDeAtuendos() {
		List<Set<Prenda>> subconjuntosDePrendas = new ArrayList<>();
		List<Set<Prenda>> combinacionesPrendasSuperiores = this.generarCombinacionesDePrendasSuperiores();
		Set<Prenda> prendasInferiores = this.filtrarPrendasPorCategoria(CategoriaPrenda.INFERIOR);
		Set<Prenda> calzados = this.filtrarPrendasPorCategoria(CategoriaPrenda.CALZADO);
		Set<Prenda> accesorios = this.filtrarPrendasPorCategoria(CategoriaPrenda.ACCESORIO);
		subconjuntosDePrendas.add(0, accesorios);
		subconjuntosDePrendas.add(0, calzados);
		subconjuntosDePrendas.add(0, prendasInferiores);
		subconjuntosDePrendas.addAll(combinacionesPrendasSuperiores);
		Set<List<Prenda>> productoCartesiano = Sets.cartesianProduct(subconjuntosDePrendas);
		return productoCartesiano.stream()
				.map(combinacion -> this.aplanarCombinacion(combinacion))
				.filter(combinacion -> combinacion.size() >= 4)
				.map(combinacion -> this.contruirAtuendoDesdeCombinacion(combinacion));
	}

	public List<Atuendo> generarAtuendos() {
		return this.generarStreamDeAtuendos().collect(Collectors.toList());
	}
}
