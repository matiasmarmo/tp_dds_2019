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
	private Guardarropa guardarropaConMuchasPrendas = new Guardarropa();
	private PrendaBuilder remeraNegraBuilder = new PrendaBuilder();
	private Prenda remeraNegra;
	private PrendaBuilder remeraBlancaBuilder = new PrendaBuilder();
	private Prenda remeraBlanca;
	private PrendaBuilder remeraRojaBuilder = new PrendaBuilder();
	private Prenda remeraRoja;
	private PrendaBuilder jeanBuilder = new PrendaBuilder();
	private Prenda jeanNegro;
	private PrendaBuilder shortBuilder = new PrendaBuilder();
	private Prenda shortArgentina;
	private PrendaBuilder jogginBuilder = new PrendaBuilder();
	private Prenda joggin;
	private PrendaBuilder zapatillasBuilder = new PrendaBuilder();
	private Prenda zapatillasNegras;
	private PrendaBuilder ojotasBuilder = new PrendaBuilder();
	private Prenda ojotasHawaianas;
	private PrendaBuilder collarDivinoBuilder = new PrendaBuilder();
	private Prenda collarDivino;
	private PrendaBuilder vinchaBuilder = new PrendaBuilder();
	private Prenda vinchaRoja;
	private Color negro = new Color(0, 0, 0);
	private Color blanco = new Color(255, 255, 255);
	private Color rojo = new Color(255, 0, 0);
	
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
		
		remeraRojaBuilder.setTipoPrenda(TipoPrenda.REMERA);
		remeraRojaBuilder.setTipoTela(TipoTela.ALGODON);
		remeraRojaBuilder.setColorPrimario(rojo);
		remeraRoja = remeraRojaBuilder.crearPrenda();
		
		jeanBuilder.setTipoPrenda(TipoPrenda.JEAN);
		jeanBuilder.setTipoTela(TipoTela.CUERO);
		jeanBuilder.setColorPrimario(negro);
		jeanNegro = jeanBuilder.crearPrenda();
		
		shortBuilder.setTipoPrenda(TipoPrenda.SHORT);
		shortBuilder.setTipoTela(TipoTela.DRY_FIT);
		shortBuilder.setColorPrimario(blanco);
		shortArgentina = shortBuilder.crearPrenda();
		
		jogginBuilder.setTipoPrenda(TipoPrenda.JOGGIN);
		jogginBuilder.setTipoTela(TipoTela.NYLON);
		jogginBuilder.setColorPrimario(negro);
		joggin = jogginBuilder.crearPrenda();
		
		zapatillasBuilder.setTipoPrenda(TipoPrenda.ZAPATILLAS);
		zapatillasBuilder.setTipoTela(TipoTela.NYLON);
		zapatillasBuilder.setColorPrimario(negro);
		zapatillasNegras = zapatillasBuilder.crearPrenda();
		
		ojotasBuilder.setTipoPrenda(TipoPrenda.OJOTAS);
		ojotasBuilder.setTipoTela(TipoTela.GOMA);
		ojotasBuilder.setColorPrimario(negro);
		ojotasHawaianas = ojotasBuilder.crearPrenda();
		
		collarDivinoBuilder.setTipoPrenda(TipoPrenda.COLLAR);
		collarDivinoBuilder.setTipoTela(TipoTela.SEDA);
		collarDivinoBuilder.setColorPrimario(blanco);
		collarDivino = collarDivinoBuilder.crearPrenda();
		
		vinchaBuilder.setTipoPrenda(TipoPrenda.VINCHA);
		vinchaBuilder.setTipoTela(TipoTela.PLASTICO);
		vinchaBuilder.setColorPrimario(rojo);
		vinchaRoja = vinchaBuilder.crearPrenda();
		
		guardarropaCompleto.agregarPrenda(remeraNegra);
		guardarropaCompleto.agregarPrenda(remeraBlanca);
		guardarropaCompleto.agregarPrenda(jeanNegro);
		guardarropaCompleto.agregarPrenda(zapatillasNegras);
		guardarropaCompleto.agregarPrenda(collarDivino);
		
		guardarropaIncompleto.agregarPrenda(remeraNegra);
		guardarropaIncompleto.agregarPrenda(jeanNegro);
		guardarropaIncompleto.agregarPrenda(collarDivino);
		
		guardarropaConMuchasPrendas.agregarPrenda(remeraNegra);
		guardarropaConMuchasPrendas.agregarPrenda(remeraBlanca);
		guardarropaConMuchasPrendas.agregarPrenda(remeraRoja);
		guardarropaConMuchasPrendas.agregarPrenda(jeanNegro);
		guardarropaConMuchasPrendas.agregarPrenda(shortArgentina);
		guardarropaConMuchasPrendas.agregarPrenda(joggin);
		guardarropaConMuchasPrendas.agregarPrenda(zapatillasNegras);
		guardarropaConMuchasPrendas.agregarPrenda(ojotasHawaianas);
		guardarropaConMuchasPrendas.agregarPrenda(collarDivino);
		guardarropaConMuchasPrendas.agregarPrenda(vinchaRoja);
	}
	
	@Test
	public void elGuardarropaCompletoGeneraDosAtuendos() {
		Assert.assertEquals(2, guardarropaCompleto.generarAtuendos().size());
	}
	
	@Test
	public void elGuardarropaIncompletoNoGeneraAtuendos() {
		Assert.assertEquals(0, guardarropaIncompleto.generarAtuendos().size());
	}
	
	@Test
	public void guardarropaConMuchasPrendas() {
		// Este guardarropa posee:
		//      3 prendas superiores
		//      3 prendas inferiores
		//      2 calzados
		//      2 accesorios
		// Cuenta que deberia realizar: 3C1 * 3C1 * 2C1 * 2C1 = 36 atuendos distintos
		Assert.assertEquals(36, guardarropaConMuchasPrendas.generarAtuendos().size());
	}
}