package dds.utn.ju_ma.group7.QueMePongo.Atuendo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import dds.utn.ju_ma.group7.QueMePongo.Prenda.ParteCuerpo;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Sensibilidad;

@Entity
public class Atuendo {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToMany
	private List<Prenda> prendasSuperiores;
	@ManyToOne
	private Prenda prendaInferior;
	@ManyToOne
	private Prenda calzado;
	@ManyToOne
	private Prenda accesorio;

	public Atuendo() {
	}

	public Atuendo(List<Prenda> prendasSuperiores, Prenda prendaInferior, Prenda calzado, Prenda accesorio) {
		this.prendasSuperiores = prendasSuperiores;
		this.prendaInferior = prendaInferior;
		this.calzado = calzado;
		this.accesorio = accesorio;
	}

	public List<Prenda> getPrendasSuperiores() {
		return this.prendasSuperiores;
	}

	public Prenda getPrendaInferior() {
		return prendaInferior;
	}

	public Prenda getCalzado() {
		return calzado;
	}

	public Prenda getAccesorio() {
		return accesorio;
	}

	public List<Prenda> todasLasPrendas() {
		List<Prenda> todas = new ArrayList<Prenda>();
		todas.addAll(this.prendasSuperiores);
		todas.add(this.prendaInferior);
		todas.add(this.calzado);
		todas.add(this.accesorio);
		return todas;
	}

	public int getNivelAbrigo() {
		return this.todasLasPrendas().stream().mapToInt(unaPrenda -> unaPrenda.getNivelAbrigo()).sum();
	}

	public double interpolacionTemperatura(double temperatura) {
		// Interpolamos un polinomio para que, dada una temperatura, nos
		// de aproximadamente el nivel de abrigo adecuado
		// Los puntos (temperatura, nivel de abrigo) usados fueron:
		// (-20,90), (-10,80), (0,60), (10,50), (20,35), (30,25) y (40,20)
		// El polinomio resultante es:
		// P(x) = (-(19x^(6)))/(144000000)+ (43x^(5))/(4800000)- (23x^(4))/(288000)-
		// (13x^(3))/(3200)+ (427x^(2))/(7200)- (71x)/(60)+60
		// (Atr matematica superior)
		return -0.000000131 * Math.pow(temperatura, 6.0) + 0.000008958 * Math.pow(temperatura, 5.0)
				- 0.000079861 * Math.pow(temperatura, 4.0) - 0.0040625 * Math.pow(temperatura, 3.0)
				+ 0.059305 * Math.pow(temperatura, 2.0) - 1.183333 * temperatura + 60;
	}

	private Map<ParteCuerpo, List<Prenda>> obtenerPrendasPorParteCuerpo() {
		List<ParteCuerpo> partesCuerpo = Arrays.asList(ParteCuerpo.class.getEnumConstants());
		return partesCuerpo.stream().collect(Collectors.toMap(key -> key, key -> new ArrayList<Prenda>()));
	}

	private boolean prendasParaParteCuerpoAdecuadas(ParteCuerpo parteCuerpo, List<Prenda> prendas,
			double temperaturaInterpolada, Sensibilidad sensibilidad) {
		double nivelAbrigo = prendas.stream().mapToInt(prenda -> prenda.getNivelAbrigo()).sum();
		double abrigoAproximado = temperaturaInterpolada * parteCuerpo.getCoeficienteAbrigoMinimo()
				+ sensibilidad.obtenerNivelSensibilidad(parteCuerpo);
		if (abrigoAproximado < 0 && nivelAbrigo != 0) {
			return false;
		}
		if (this.getNivelAbrigo() * 0.7 < nivelAbrigo) {
			return false;
		}
		return nivelAbrigo * 0.5 <= abrigoAproximado && nivelAbrigo * 1.5 >= abrigoAproximado;
	}

	public boolean esAdecuadoATemperatura(double temperatura, Sensibilidad sensibilidad) {
		Map<ParteCuerpo, List<Prenda>> prendasPorParteCuerpo = this.obtenerPrendasPorParteCuerpo();
		this.todasLasPrendas().forEach(prenda -> prendasPorParteCuerpo.get(prenda.getParteCuerpo()).add(prenda));
		double temperaturaInterpolada = this.interpolacionTemperatura(temperatura);
		boolean resultado = prendasPorParteCuerpo.entrySet().stream()
				.allMatch(entry -> this.prendasParaParteCuerpoAdecuadas(entry.getKey(), entry.getValue(),
						temperaturaInterpolada, sensibilidad));
		return resultado;
	}
}
