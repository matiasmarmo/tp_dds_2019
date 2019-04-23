package dds.utn.ju_ma.group7.QueMePongo.Prenda;

import java.util.List;
import java.util.Arrays;

public enum TipoPrenda {

	REMERA, BUZO,
	JEAN, JOGGIN,SHORT,
	ZAPATILLAS, OJOTAS,
	COLLAR, VINCHA;
	
	private List<TipoTela> telasPosibles;
	private CategoriaPrenda categoria;
	
	static {
		REMERA.telasPosibles = Arrays.asList(TipoTela.ALGODON, TipoTela.NYLON);
		REMERA.categoria = CategoriaPrenda.SUPERIOR;
		BUZO.telasPosibles = Arrays.asList(TipoTela.ALGODON, TipoTela.NYLON);
		BUZO.categoria = CategoriaPrenda.SUPERIOR;
		JEAN.telasPosibles = Arrays.asList(TipoTela.CUERO);
		JEAN.categoria = CategoriaPrenda.INFERIOR;
		JOGGIN.telasPosibles = Arrays.asList(TipoTela.NYLON);
		JOGGIN.categoria = CategoriaPrenda.INFERIOR;
		SHORT.telasPosibles = Arrays.asList(TipoTela.DRY_FIT);
		SHORT.categoria = CategoriaPrenda.INFERIOR;
		ZAPATILLAS.telasPosibles = Arrays.asList(TipoTela.NYLON);
		ZAPATILLAS.categoria = CategoriaPrenda.CALZADO;
		OJOTAS.telasPosibles = Arrays.asList(TipoTela.GOMA);
		OJOTAS.categoria = CategoriaPrenda.CALZADO;
		COLLAR.telasPosibles = Arrays.asList(TipoTela.SEDA);
		COLLAR.categoria = CategoriaPrenda.ACCESORIO;
		VINCHA.telasPosibles = Arrays.asList(TipoTela.PLASTICO);
		VINCHA.categoria = CategoriaPrenda.ACCESORIO;
	}
	
	public CategoriaPrenda getCategoria() {
		return this.categoria;
	}
	
	public boolean esTelaValida(TipoTela tela) {
		return this.telasPosibles.contains(tela);
	}
}
