package dds.utn.ju_ma.group7.QueMePongo.Atuendo;
import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;

public class Atuendo {
	
	private final List<Prenda> prendasSuperiores;
	private final Prenda prendaInferior;
	private final Prenda calzado;
	private final Prenda accesorio;
	
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
}
