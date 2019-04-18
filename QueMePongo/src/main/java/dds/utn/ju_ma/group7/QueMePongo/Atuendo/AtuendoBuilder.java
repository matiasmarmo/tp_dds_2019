package dds.utn.ju_ma.group7.QueMePongo.Atuendo;

import dds.utn.ju_ma.group7.QueMePongo.Prenda.CategoriaPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;

public class AtuendoBuilder {

	private Prenda prendaSuperior;
	private Prenda prendaInferior;
	private Prenda calzado;
	private Prenda accesorio;

	public AtuendoBuilder(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado, Prenda accesorio) {
		this.prendaSuperior = prendaSuperior;
		this.prendaInferior = prendaInferior;
		this.calzado = calzado;
		this.accesorio = accesorio;
	}

	public void setPrendaSuperior(Prenda prendaSuperior) {
		this.prendaSuperior = prendaSuperior;
	}

	public void setPrendaInferior(Prenda prendaInferior) {
		this.prendaInferior = prendaInferior;
	}

	public void setCalzado(Prenda calzado) {
		this.calzado = calzado;
	}

	public void setAccesorio(Prenda accesorio) {
		this.accesorio = accesorio;
	}

	private void verificarCategoria(Prenda prenda, CategoriaPrenda categoria) {
		if (prenda.getCategoria() != categoria) {
			throw new AtuendoInvalidoException("La categoria de una prenda fue invalida");
		}
	}

	public Atuendo crearAtuendo() {
		verificarCategoria(this.prendaSuperior, CategoriaPrenda.SUPERIOR);
		verificarCategoria(this.prendaInferior, CategoriaPrenda.INFERIOR);
		verificarCategoria(this.calzado, CategoriaPrenda.CALZADO);
		verificarCategoria(this.accesorio, CategoriaPrenda.ACCESORIO);
		return new Atuendo(this.prendaSuperior, this.prendaInferior, this.calzado, this.accesorio);
	}
}
