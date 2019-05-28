package dds.utn.ju_ma.group7.QueMePongo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.*;
import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.*;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.*;

public class SugeridorTest extends Fixture {
	
	private PrendaBuilder remeraNegraBuilder = new PrendaBuilder();
	private Prenda remeraNegra;

	private PrendaBuilder remeraBlancaBuilder = new PrendaBuilder();
	private Prenda remeraBlanca;

	private PrendaBuilder pantalonNegroBuilder = new PrendaBuilder();
	private Prenda pantalonNegro;

	private PrendaBuilder pantalonBlancoBuilder = new PrendaBuilder();
	private Prenda pantalonBlanco;

	private PrendaBuilder zapatosNegrosBuilder = new PrendaBuilder();
	private Prenda zapatosNegros;

	private PrendaBuilder zapatosBlancosBuilder = new PrendaBuilder();
	private Prenda zapatosBlancos;

	private PrendaBuilder collarBuilder = new PrendaBuilder();
	private Prenda collar;

	private Color negro = new Color(0, 0, 0);
	
	private ProveedorMockVerano proveedor1 = new ProveedorMockVerano();
	private ProveedorMockInvierno proveedor2 = new ProveedorMockInvierno();
	private Sugeridor sug = new Sugeridor();
	
	private Calendar fecha1 = Calendar.getInstance();
	
	List<Prenda> prendasSup = new ArrayList<Prenda>();
	List<Atuendo> atuendos = new ArrayList<Atuendo>();
	Atuendo atuendoNegro = new Atuendo(prendasSup,pantalonNegro,zapatosNegros,collar);
	
	public void init() {
		
		remeraNegraBuilder.setTipoPrenda(TipoPrenda.REMERA).setTipoTela(TipoTela.ALGODON).setColorPrimario(negro);
		remeraNegra = remeraNegraBuilder.crearPrenda();

		remeraBlancaBuilder.setTipoPrenda(TipoPrenda.REMERA).setTipoTela(TipoTela.ALGODON).setColorPrimario(blanco);
		remeraBlanca = remeraBlancaBuilder.crearPrenda();

		pantalonNegroBuilder.setTipoPrenda(TipoPrenda.JOGGIN).setTipoTela(TipoTela.NYLON).setColorPrimario(negro);
		pantalonNegro = pantalonNegroBuilder.crearPrenda();

		pantalonBlancoBuilder.setTipoPrenda(TipoPrenda.JOGGIN).setTipoTela(TipoTela.NYLON).setColorPrimario(blanco);
		pantalonBlanco = pantalonBlancoBuilder.crearPrenda();

		zapatosNegrosBuilder.setTipoPrenda(TipoPrenda.ZAPATILLAS).setTipoTela(TipoTela.NYLON).setColorPrimario(negro);
		zapatosNegros = zapatosNegrosBuilder.crearPrenda();

		zapatosBlancosBuilder.setTipoPrenda(TipoPrenda.ZAPATILLAS).setTipoTela(TipoTela.NYLON).setColorPrimario(blanco);
		zapatosBlancos = zapatosBlancosBuilder.crearPrenda();

		collarBuilder.setTipoPrenda(TipoPrenda.COLLAR).setTipoTela(TipoTela.SEDA).setColorPrimario(negro);
		collar = collarBuilder.crearPrenda();
		
		prendasSup.add(remeraNegra);
		
		
		atuendos.add(atuendoNegro);
		
		sug.setProveedorClima(proveedor2);
		fecha1.set(2019, 05, 27, 18, 0);
	}


	@Test
	public void sugiere() {
		Assert.assertEquals(sug.sugerir(atuendos, fecha1).size(), 1);
	}
	

	@Test(expected = NullPointerException.class)
	public void asd(){
		sug.sugerir(atuendos, fecha1);
	}

}