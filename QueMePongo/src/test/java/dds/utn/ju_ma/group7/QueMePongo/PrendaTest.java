package dds.utn.ju_ma.group7.QueMePongo;

import org.junit.Test;
import org.junit.Assert;

import dds.utn.ju_ma.group7.QueMePongo.Prenda.*;

public class PrendaTest extends Fixture {

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
		remeraDeCueroBuilder.crearPrenda();
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
		remeraColoresInvalidosBuilder.crearPrenda();
	}

	@Test(expected = PrendaInvalidaException.class)
	public void losParametrosTipoPrendaTipoTelaColorPrimarioSonObligatorios() {
		remeraNulaBuilder.crearPrenda();
	}

	@Test
	public void sePuedeConsultarLaCategoriaDeLaPrenda() {
		Assert.assertFalse(remeraNegra.esDeCategoria(CategoriaPrenda.INFERIOR));
	}
	
	@Test(expected = ImagenInvalidaException.class)
	public void elPathDeLaImagenDeLaPrendaNoPuedeSerNulo() {
		remeraNegra.setImagen(null);
	}
	
	@Test(expected = ImagenInvalidaException.class)
	public void elPathDeLaImagenDeLaPrendaDebeSerValido() {
		remeraNegra.setImagen("");
	}

}
