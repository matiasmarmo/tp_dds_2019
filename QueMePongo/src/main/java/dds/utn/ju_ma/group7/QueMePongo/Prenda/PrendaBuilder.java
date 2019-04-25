package dds.utn.ju_ma.group7.QueMePongo.Prenda;

import java.util.Arrays;
import java.util.Objects;

public class PrendaBuilder {
	private TipoPrenda tipoPrenda;
	private TipoTela tipoTela;
	private Color colorPrimario;
	private Color colorSecundario;

	public PrendaBuilder setTipoPrenda(TipoPrenda tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
		return this;
	}

	public PrendaBuilder setTipoTela(TipoTela tipoTela) {
		this.tipoTela = tipoTela;
		return this;
	}

	public PrendaBuilder setColorPrimario(Color colorPrimario) {
		this.colorPrimario = colorPrimario;
		return this;
	}

	public PrendaBuilder setColorSecundario(Color colorSecundario) {
		this.colorSecundario = colorSecundario;
		return this;
	}

	public Prenda crearPrenda() {
		this.validarTodo();
		return new Prenda(this.tipoPrenda, this.tipoTela, this.colorPrimario, this.colorSecundario);
	}

	private void validarTodo() {
		Arrays.asList(this.tipoPrenda, this.tipoTela, this.colorPrimario)
				.forEach(elemento -> this.validarNulo(elemento));
		
		if (!this.tipoPrenda.esTelaValida(this.tipoTela)) {
			throw new PrendaInvalidaException("La tela elegida no se corresponde con el tipo de prenda");
		}
		
		if (this.colorSecundario != null && this.colorPrimario.esIgualA(this.colorSecundario)) {
			throw new PrendaInvalidaException("El color secundario debe ser diferente del color primario");
		}

	}

	private void validarNulo(Object elemento) {
		if (elemento == null) {
			throw new PrendaInvalidaException("Los parametros obligatorios no pueden ser nulos");
		}
	}
}
