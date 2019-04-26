package dds.utn.ju_ma.group7.QueMePongo.Prenda;

public class Prenda {
	private final TipoPrenda tipoPrenda;
	private final TipoTela tipoTela;
	private final Color colorPrimario;
	private final Color colorSecundario;

	public Prenda(TipoPrenda tipoPrenda, TipoTela tipoTela, Color colorPrimario, Color colorSecundario) {
		this.tipoPrenda = tipoPrenda;
		this.tipoTela = tipoTela;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
	}

	public TipoPrenda getTipoPrenda() {
		return tipoPrenda;
	}

	public TipoTela getTipoTela() {
		return tipoTela;
	}

	public Color getColorPrimario() {
		return colorPrimario;
	}

	public Color getColorSecundario() {
		return colorSecundario;
	}

	public CategoriaPrenda getCategoria() {
		return this.tipoPrenda.getCategoria();
	}
	
	public boolean esDeCategoria(CategoriaPrenda categoria) {
		return this.getCategoria() == categoria;
	}
	
}
