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
	private int nivelAbrigo;
	private int jerarquia;
	
	static {
		REMERA.telasPosibles = Arrays.asList(TipoTela.ALGODON, TipoTela.NYLON);
		REMERA.categoria = CategoriaPrenda.SUPERIOR;
		REMERA.nivelAbrigo = 10;
		REMERA.jerarquia = 0;
		
		BUZO.telasPosibles = Arrays.asList(TipoTela.ALGODON, TipoTela.NYLON);
		BUZO.categoria = CategoriaPrenda.SUPERIOR;
		BUZO.nivelAbrigo = 30;
		BUZO.jerarquia = 2;
		
		JEAN.telasPosibles = Arrays.asList(TipoTela.CUERO);
		JEAN.categoria = CategoriaPrenda.INFERIOR;
		JEAN.nivelAbrigo = 10;
		
		JOGGIN.telasPosibles = Arrays.asList(TipoTela.NYLON);
		JOGGIN.categoria = CategoriaPrenda.INFERIOR;
		JOGGIN.nivelAbrigo = 5;
		
		SHORT.telasPosibles = Arrays.asList(TipoTela.DRY_FIT);
		SHORT.categoria = CategoriaPrenda.INFERIOR;
		SHORT.nivelAbrigo = 3;
		
		ZAPATILLAS.telasPosibles = Arrays.asList(TipoTela.NYLON);
		ZAPATILLAS.categoria = CategoriaPrenda.CALZADO;
		ZAPATILLAS.nivelAbrigo = 20;
		
		OJOTAS.telasPosibles = Arrays.asList(TipoTela.GOMA);
		OJOTAS.categoria = CategoriaPrenda.CALZADO;
		OJOTAS.nivelAbrigo = 5;
		
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
	
	public int getNivelAbrigo() {
		return this.nivelAbrigo;
	}
	
	public int getJerarquia() {
		return this.jerarquia;
	}
}
