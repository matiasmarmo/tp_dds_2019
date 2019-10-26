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
	private ParteCuerpo parteCuerpo;
	private int nivelAbrigo;
	private int jerarquia;
	
	static {
		REMERA.telasPosibles = Arrays.asList(TipoTela.ALGODON, TipoTela.NYLON);
		REMERA.categoria = CategoriaPrenda.SUPERIOR;
		REMERA.parteCuerpo = ParteCuerpo.TORSO;
		REMERA.nivelAbrigo = 15;
		REMERA.jerarquia = 0;
		
		BUZO.telasPosibles = Arrays.asList(TipoTela.ALGODON, TipoTela.NYLON);
		BUZO.categoria = CategoriaPrenda.SUPERIOR;
		BUZO.parteCuerpo = ParteCuerpo.TORSO;
		BUZO.nivelAbrigo = 30;
		BUZO.jerarquia = 2;
		
		JEAN.telasPosibles = Arrays.asList(TipoTela.CUERO);
		JEAN.categoria = CategoriaPrenda.INFERIOR;
		JEAN.parteCuerpo = ParteCuerpo.PIERNAS;
		JEAN.nivelAbrigo = 10;
		
		JOGGIN.telasPosibles = Arrays.asList(TipoTela.NYLON);
		JOGGIN.categoria = CategoriaPrenda.INFERIOR;
		JOGGIN.parteCuerpo = ParteCuerpo.PIERNAS;
		JOGGIN.nivelAbrigo = 12;
		
		SHORT.telasPosibles = Arrays.asList(TipoTela.DRY_FIT);
		SHORT.categoria = CategoriaPrenda.INFERIOR;
		SHORT.parteCuerpo = ParteCuerpo.PIERNAS;
		SHORT.nivelAbrigo = 7;
		
		ZAPATILLAS.telasPosibles = Arrays.asList(TipoTela.NYLON, TipoTela.CUERO);
		ZAPATILLAS.categoria = CategoriaPrenda.CALZADO;
		ZAPATILLAS.parteCuerpo = ParteCuerpo.PIES;
		ZAPATILLAS.nivelAbrigo = 15;
		
		OJOTAS.telasPosibles = Arrays.asList(TipoTela.GOMA);
		OJOTAS.categoria = CategoriaPrenda.CALZADO;
		OJOTAS.parteCuerpo = ParteCuerpo.PIES;
		OJOTAS.nivelAbrigo = 5;
		
		COLLAR.telasPosibles = Arrays.asList(TipoTela.SEDA, TipoTela.PLASTICO);
		COLLAR.categoria = CategoriaPrenda.ACCESORIO;
		COLLAR.parteCuerpo = ParteCuerpo.CUELLO;
		COLLAR.nivelAbrigo = 0;
		
		VINCHA.telasPosibles = Arrays.asList(TipoTela.PLASTICO);
		VINCHA.categoria = CategoriaPrenda.ACCESORIO;
		VINCHA.parteCuerpo = ParteCuerpo.CABEZA;
		VINCHA.nivelAbrigo = 0;
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
	
	public ParteCuerpo getParteCuerpo() {
		return this.parteCuerpo;
	}

	public List<TipoTela> getTelasPosibles() {
		return telasPosibles;
	}
	
}
