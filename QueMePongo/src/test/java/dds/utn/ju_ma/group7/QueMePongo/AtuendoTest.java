package dds.utn.ju_ma.group7.QueMePongo;

import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.Sensibilidad;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.TipoSensibilidad;

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
		Assert.assertTrue(atuendoNegro.esAdecuadoATemperatura(25, new Sensibilidad()));
	}
	
	@Test
	public void elAtuendoNormalNoEsAdecuadoEn5Grados() {
		Assert.assertFalse(atuendoNegro.esAdecuadoATemperatura(5, new Sensibilidad()));
	}
	
	@Test
	public void elAtuendoConBuzoEsAdecuadoEn10Grados() {
		Assert.assertTrue(atuendoNegroConBuzo.esAdecuadoATemperatura(10, new Sensibilidad()));
	}
	
	@Test
	public void elAtuendoConBuzoNoEsAdecuadoEn50Grados() {
		Assert.assertFalse(atuendoNegroConBuzo.esAdecuadoATemperatura(50, new Sensibilidad()));
	}
	
	@Test
	public void usuarioSensibleUsaBuzoCuandoHaceCalor() {
		Sensibilidad sensibilidad = new Sensibilidad();
		sensibilidad.setNivelSensibilidad(TipoSensibilidad.CABEZA, 50);
		Assert.assertTrue(atuendoNegroConBuzo.esAdecuadoATemperatura(25, sensibilidad));
	}
}
