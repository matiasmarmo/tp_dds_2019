package dds.utn.ju_ma.group7.QueMePongo;

import dds.utn.ju_ma.group7.QueMePongo.Prenda.Color;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.PrendaBuilder;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoTela;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Guardarropa;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class GuardarropaTest {
	
	private Guardarropa guardarropaCompleto = new Guardarropa();
	private Guardarropa guardarropaIncompleto = new Guardarropa();
	private PrendaBuilder remeraNegraBuilder = new PrendaBuilder();
	private Prenda remeraNegra;
	private PrendaBuilder remeraBlancaBuilder = new PrendaBuilder();
	private Prenda remeraBlanca;
	private PrendaBuilder jeanBuilder = new PrendaBuilder();
	private Prenda jeanNegro;
	private PrendaBuilder zapatillasBuilder = new PrendaBuilder();
	private Prenda zapatillasNegras;
	private PrendaBuilder collarDivinoBuilder = new PrendaBuilder();
	private Prenda collarDivino;
	private Color negro = new Color(0, 0, 0);
	private Color blanco = new Color(255, 255, 255);
	
	@Before
	public void init() {
		
		remeraNegraBuilder.setTipoPrenda(TipoPrenda.REMERA);
		remeraNegraBuilder.setTipoTela(TipoTela.ALGODON);
		remeraNegraBuilder.setColorPrimario(negro);
		remeraNegra = remeraNegraBuilder.crearPrenda();
		
		remeraBlancaBuilder.setTipoPrenda(TipoPrenda.REMERA);
		remeraBlancaBuilder.setTipoTela(TipoTela.ALGODON);
		remeraBlancaBuilder.setColorPrimario(blanco);
		remeraBlanca = remeraBlancaBuilder.crearPrenda();
		
		jeanBuilder.setTipoPrenda(TipoPrenda.JEAN);
		jeanBuilder.setTipoTela(TipoTela.CUERO);
		jeanBuilder.setColorPrimario(negro);
		jeanNegro = jeanBuilder.crearPrenda();
		
		zapatillasBuilder.setTipoPrenda(TipoPrenda.ZAPATILLAS);
		zapatillasBuilder.setTipoTela(TipoTela.NYLON);
		zapatillasBuilder.setColorPrimario(negro);
		zapatillasNegras = zapatillasBuilder.crearPrenda();
		
		collarDivinoBuilder.setTipoPrenda(TipoPrenda.COLLAR);
		collarDivinoBuilder.setTipoTela(TipoTela.SEDA);
		collarDivinoBuilder.setColorPrimario(blanco);
		collarDivino = collarDivinoBuilder.crearPrenda();
		
		guardarropaCompleto.agregarPrenda(remeraNegra);
		guardarropaCompleto.agregarPrenda(remeraBlanca);
		guardarropaCompleto.agregarPrenda(jeanNegro);
		guardarropaCompleto.agregarPrenda(zapatillasNegras);
		guardarropaCompleto.agregarPrenda(collarDivino);
		
		guardarropaIncompleto.agregarPrenda(remeraNegra);
		guardarropaIncompleto.agregarPrenda(jeanNegro);
		guardarropaIncompleto.agregarPrenda(collarDivino);
	}
	
	@Test
	public void elGuardarropaCompletoGeneraDosAtuendos() {
		Assert.assertEquals(2, guardarropaCompleto.generarAtuendos().size());
	}
	
	@Test
	public void elGuardarropaIncompletoNoGeneraAtuendos() {
		Assert.assertEquals(0, guardarropaIncompleto.generarAtuendos().size());
	}
}