package dds.utn.ju_ma.group7.QueMePongo;

import org.junit.Before;

import dds.utn.ju_ma.group7.QueMePongo.Prenda.Color;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.PrendaBuilder;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoTela;

public class Fixture {

	protected PrendaBuilder remeraNegraBuilder = new PrendaBuilder();
	protected Prenda remeraNegra;
	protected Color negro = new Color(0, 0, 0);
	protected Color blanco = new Color(255, 255, 255);

	@Before
	public void initFixture() {

		remeraNegraBuilder.setTipoPrenda(TipoPrenda.REMERA);
		remeraNegraBuilder.setTipoTela(TipoTela.ALGODON);
		remeraNegraBuilder.setColorPrimario(negro);
		remeraNegra = remeraNegraBuilder.crearPrenda();

	}
}
