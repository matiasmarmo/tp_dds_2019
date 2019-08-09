package dds.utn.ju_ma.group7.QueMePongo.Guardarropa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Sets;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.CategoriaPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.InterfazPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.PrendaNula;

public class GeneradorCombinaciones {

	private static Set<InterfazPrenda> filtrarPrendasPorCategoria(List<Prenda> prendas, CategoriaPrenda categoria) {
		return prendas.stream().filter(prenda -> prenda.esDeCategoria(categoria)).collect(Collectors.toSet());
	}

	private static Atuendo contruirAtuendoDesdeCombinacion(List<Prenda> combinacion) {
		Prenda accesorio = combinacion.get(0);
		Prenda calzado = combinacion.get(1);
		Prenda inferior = combinacion.get(2);
		List<Prenda> prendasSuperiores = combinacion.subList(3, combinacion.size());
		return new Atuendo(prendasSuperiores, inferior, calzado, accesorio);
	}

	private static List<Prenda> sacarPrendaNulaDeCombinacion(List<InterfazPrenda> combinacion) {
		return combinacion.stream().filter(prenda -> !prenda.esPrendaNula()).map(prenda -> (Prenda) prenda)
				.collect(Collectors.toList());
	}

	private static Set<InterfazPrenda> agregarPrendaNulaAConjuntoDePrendas(Set<Prenda> conjuntoPrendas) {
		Set<InterfazPrenda> conjuntoInterfazPrenda = conjuntoPrendas.stream()
				.map(prenda -> (InterfazPrenda) prenda)
				.collect(Collectors.toSet());
		conjuntoInterfazPrenda.add(new PrendaNula());
		return conjuntoInterfazPrenda;
	}

	private static List<Set<InterfazPrenda>> generarCombinacionesDePrendasSuperiores(List<Prenda> prendas) {
		return prendas.stream()
				.filter(prenda -> prenda.esDeCategoria(CategoriaPrenda.SUPERIOR))
				.collect(Collectors.groupingBy(Prenda::getJerarquia, Collectors.toSet()))
				.values().stream()
				.map(conjunto -> GeneradorCombinaciones.agregarPrendaNulaAConjuntoDePrendas(conjunto))
				.collect(Collectors.toList());
	}

	public static Stream<Atuendo> generarStreamDeAtuendos(List<Prenda> prendas) {
		Set<List<InterfazPrenda>> productoCartesiano = generarSubconjuntoDePrendas(prendas);
		return productoCartesiano.stream()
				.map(combinacion -> GeneradorCombinaciones.sacarPrendaNulaDeCombinacion(combinacion))
				.filter(combinacion -> combinacion.size() >= 4)
				.map(combinacion -> GeneradorCombinaciones.contruirAtuendoDesdeCombinacion(combinacion));
	}

	private static Set<List<InterfazPrenda>> generarSubconjuntoDePrendas(List<Prenda> prendas) {
		List<Set<InterfazPrenda>> subconjuntosDePrendas = new ArrayList<>();
		List<Set<InterfazPrenda>> combinacionesPrendasSuperiores = GeneradorCombinaciones
				.generarCombinacionesDePrendasSuperiores(prendas);
		Set<InterfazPrenda> prendasInferiores = GeneradorCombinaciones.filtrarPrendasPorCategoria(prendas,
				CategoriaPrenda.INFERIOR);
		Set<InterfazPrenda> calzados = GeneradorCombinaciones.filtrarPrendasPorCategoria(prendas,
				CategoriaPrenda.CALZADO);
		Set<InterfazPrenda> accesorios = GeneradorCombinaciones.filtrarPrendasPorCategoria(prendas,
				CategoriaPrenda.ACCESORIO);
		subconjuntosDePrendas.add(accesorios);
		subconjuntosDePrendas.add(calzados);
		subconjuntosDePrendas.add(prendasInferiores);
		subconjuntosDePrendas.addAll(combinacionesPrendasSuperiores);
		Set<List<InterfazPrenda>> productoCartesiano = Sets.cartesianProduct(subconjuntosDePrendas);
		return productoCartesiano;
	}

	public static List<Atuendo> generarAtuendos(List<Prenda> prendas) {
		return GeneradorCombinaciones.generarStreamDeAtuendos(prendas).collect(Collectors.toList());
	}

}
