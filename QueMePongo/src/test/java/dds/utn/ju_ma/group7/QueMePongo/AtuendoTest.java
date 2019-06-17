package dds.utn.ju_ma.group7.QueMePongo;

import org.junit.Test;

import org.junit.Assert;

public class AtuendoTest extends Fixture {
	
	@Test
	public void elAtuendoSinBuzoTieneNivelDeAbrigo40() { 
		Assert.assertEquals(40, atuendoNegro.getNivelAbrigo());
	}

	@Test
	public void elAtuendoConBuzoTieneNivelDeAbrigo70() {
		Assert.assertEquals(70, atuendoNegroConBuzo.getNivelAbrigo());
	}
	
	@Test
	public void elAtuendoNormalEsAdecuadoEn25Grados() {
		Assert.assertTrue(atuendoNegro.esAdecuadoATemperatura(25));
	}
	
	@Test
	public void elAtuendoNormalNoEsAdecuadoEn5Grados() {
		Assert.assertFalse(atuendoNegro.esAdecuadoATemperatura(5));
	}
	
	@Test
	public void elAtuendoConBuzoEsAdecuadoEn10Grados() {
		Assert.assertTrue(atuendoNegroConBuzo.esAdecuadoATemperatura(10));
	}
	
	@Test
	public void elAtuendoConBuzoNoEsAdecuadoEn50Grados() {
		Assert.assertFalse(atuendoNegroConBuzo.esAdecuadoATemperatura(50));
	}
}
