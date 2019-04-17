package dds.utn.ju_ma.group7.QueMePongo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;

public class Atuendo {
	
	private final Prenda prendaSuperior;
	private final Prenda prendaInferior;
	private final Prenda calzado;
	private final Prenda accesorio;
	
	public Atuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado, Prenda accesorio) {
		this.prendaSuperior = prendaSuperior;
		this.prendaInferior = prendaInferior;
		this.calzado = calzado;
		this.accesorio = accesorio;
	}

	public Prenda getPrendaSuperior() {
		return prendaSuperior;
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
}
