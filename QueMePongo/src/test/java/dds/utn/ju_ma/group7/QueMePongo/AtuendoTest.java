package dds.utn.ju_ma.group7.QueMePongo;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import dds.utn.ju_ma.group7.QueMePongo.Prenda.*;
import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Atuendo.AtuendoBuilder;
import dds.utn.ju_ma.group7.QueMePongo.Atuendo.AtuendoInvalidoException;


public class AtuendoTest {
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
	private Color blanco = new Color(255, 255, 255);
	
	private AtuendoBuilder atuendoNegroBuilder = new AtuendoBuilder();
	private Atuendo atuendoNegroValido;
	
	private AtuendoBuilder atuendoSinCollarBuilder = new AtuendoBuilder();
	private Atuendo atuendoSinCollar;
	
	private AtuendoBuilder atuendoDosRemerasBuilder = new AtuendoBuilder();
	private Atuendo atuendoDosRemeras;
	
	private AtuendoBuilder atuendoDosPantalonesBuilder = new AtuendoBuilder();
	private Atuendo atuendoDosPantalones;
	
	private AtuendoBuilder atuendoDosZapatosBuilder = new AtuendoBuilder();
	private Atuendo atuendoDosZapatos;
	
	
	@Before
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
		
		atuendoNegroBuilder.setPrendaSuperior(remeraNegra);
		atuendoNegroBuilder.setPrendaInferior(pantalonNegro);
		atuendoNegroBuilder.setCalzado(zapatosNegros);
		atuendoNegroBuilder.setAccesorio(collar);
		atuendoNegroValido = atuendoNegroBuilder.crearAtuendo();
		
		atuendoSinCollarBuilder.setPrendaSuperior(remeraBlanca);
		atuendoSinCollarBuilder.setPrendaInferior(pantalonBlanco);
		atuendoSinCollarBuilder.setCalzado(zapatosBlancos);
		atuendoSinCollar = atuendoSinCollarBuilder.crearAtuendo();
		
		atuendoDosRemerasBuilder.setPrendaSuperior(remeraBlanca);
		atuendoDosRemerasBuilder.setPrendaInferior(remeraNegra);
		atuendoDosRemerasBuilder.setCalzado(zapatosNegros);
		atuendoNegroBuilder.setAccesorio(collar);
		
		atuendoDosPantalonesBuilder.setPrendaSuperior(remeraBlanca);
		atuendoDosPantalonesBuilder.setPrendaInferior(pantalonNegro);
		atuendoDosPantalonesBuilder.setCalzado(pantalonBlanco);
		atuendoDosPantalonesBuilder.setAccesorio(collar);
		
		atuendoDosZapatosBuilder.setPrendaSuperior(remeraBlanca);
		atuendoDosZapatosBuilder.setPrendaInferior(zapatosBlancos);
		atuendoDosZapatosBuilder.setCalzado(zapatosNegros);
		atuendoDosZapatosBuilder.setAccesorio(collar);
		
	}

	@Test
	public void primerAtuendoCorrecto() {
		Assert.assertTrue(atuendoNegroValido.getPrendaSuperior().esDeCategoria(CategoriaPrenda.SUPERIOR));
	}
	
	@Test
	public void segundoAtuendoCorrecto() {
		Assert.assertTrue(atuendoSinCollar.getPrendaInferior().esDeCategoria(CategoriaPrenda.INFERIOR));
	}
	
	@Test(expected = AtuendoInvalidoException.class)
	public void atuendoDosRemerasInvalido() {
		atuendoDosRemeras = atuendoDosRemerasBuilder.crearAtuendo();
	}
	
	@Test(expected = AtuendoInvalidoException.class)
	public void atuendoDosPantalonesInvalido() {
		atuendoDosPantalones = atuendoDosPantalonesBuilder.crearAtuendo();
	}
	
	@Test(expected = AtuendoInvalidoException.class)
	public void atuendoDosZapatosInvalido() {
		atuendoDosZapatos = atuendoDosZapatosBuilder.crearAtuendo();
	}
}