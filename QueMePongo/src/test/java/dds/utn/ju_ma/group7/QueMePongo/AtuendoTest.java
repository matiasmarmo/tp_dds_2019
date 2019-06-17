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
}
