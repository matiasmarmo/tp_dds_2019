package dds.utn.ju_ma.group7.QueMePongo;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


import dds.utn.ju_ma.group7.QueMePongo.Prenda.*;


public class PrendaTest {
	private PrendaBuilder remeraNegraBuilder = new PrendaBuilder();
	private Prenda remeraNegra;
	
	private PrendaBuilder remeraNegraYBlancaBuilder = new PrendaBuilder();
	private Prenda remeraNegraYBlanca;
	
	private PrendaBuilder remeraDeCueroBuilder = new PrendaBuilder();
	private Prenda remeraDeCuero;
	
	private PrendaBuilder remeraColoresInvalidosBuilder = new PrendaBuilder();
	private Prenda remeraColoresInvalidos;
	
	private PrendaBuilder remeraNulaBuilder = new PrendaBuilder();
	private Prenda remeraNula;
	
	private Color negro = new Color(0, 0, 0);
	private Color blanco = new Color(255, 255, 255);
	
	@Before
	public void init() {
		remeraNegraBuilder.setTipoPrenda(TipoPrenda.REMERA);
		remeraNegraBuilder.setTipoTela(TipoTela.ALGODON);
		remeraNegraBuilder.setColorPrimario(negro);
		remeraNegra = remeraNegraBuilder.crearPrenda();
		
		remeraNegraYBlancaBuilder.setTipoPrenda(TipoPrenda.REMERA);
		remeraNegraYBlancaBuilder.setTipoTela(TipoTela.ALGODON);
		remeraNegraYBlancaBuilder.setColorPrimario(negro);
		remeraNegraYBlancaBuilder.setColorSecundario(blanco);
		
		remeraDeCueroBuilder.setTipoPrenda(TipoPrenda.REMERA);
		remeraDeCueroBuilder.setTipoTela(TipoTela.CUERO);
		remeraDeCueroBuilder.setColorPrimario(negro);
		
		remeraColoresInvalidosBuilder.setTipoPrenda(TipoPrenda.REMERA);
		remeraColoresInvalidosBuilder.setTipoTela(TipoTela.ALGODON);
		remeraColoresInvalidosBuilder.setColorPrimario(negro);
		remeraColoresInvalidosBuilder.setColorSecundario(negro);
		
		
	}
	
	@Test
	public void sePuedeSaberElTipoPrenda() {
		Assert.assertEquals(TipoPrenda.REMERA, remeraNegra.getTipoPrenda());
	}
	
	@Test
	public void laCategoriaEsConsistenteConElTipoPrenda() {
		Assert.assertEquals(CategoriaPrenda.SUPERIOR, remeraNegra.getCategoria());
	}
	
	@Test(expected = PrendaInvalidaException.class)
	public void tipoTelaCorrespondeAlTipoPrenda() {
		remeraDeCuero = remeraDeCueroBuilder.crearPrenda();
	}
	
	@Test
	public void prendaPuedeTenerColorPrimarioYSecundario() {
		remeraNegraYBlanca = remeraNegraYBlancaBuilder.crearPrenda();
		Assert.assertTrue(remeraNegraYBlanca.getColorSecundario().esIgualA(blanco));
	}
	
	@Test
	public void elColorSecundarioEsOpcional() {
		Assert.assertNull(remeraNegra.getColorSecundario());
	}
	
	@Test(expected = PrendaInvalidaException.class)
	public void elColorSecundarioDebeSerDistintoAlPrimario() {
		remeraColoresInvalidos = remeraColoresInvalidosBuilder.crearPrenda();
	}
	
	@Test(expected = PrendaInvalidaException.class)
	public void losParametrosTipoPrendaTipoTelaColorPrimarioSonObligatorios() {
		remeraNula = remeraNulaBuilder.crearPrenda();
	}
	
	@Test
	public void sePuedeConsultarLaCategoriaDeLaPrenda() {
		Assert.assertFalse(remeraNegra.esDeCategoria(CategoriaPrenda.INFERIOR));
	}

}
