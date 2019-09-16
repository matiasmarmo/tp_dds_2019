package dds.utn.ju_ma.group7.QueMePongo;

import org.junit.Assert;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Prenda.ParteCuerpo;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Sensibilidad;

public class AtuendoTest extends Fixture {
	
	@Test
	public void elAtuendoSinBuzoTieneNivelDeAbrigo37() { 
		Assert.assertEquals(37, atuendoNegro.getNivelAbrigo());
	}

	@Test
	public void elAtuendoConBuzoTieneNivelDeAbrigo72() {
		Assert.assertEquals(72, atuendoNegroConBuzo.getNivelAbrigo());
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
		sensibilidad.setNivelSensibilidad(ParteCuerpo.TORSO, 50);
		Assert.assertTrue(atuendoNegroConBuzo.esAdecuadoATemperatura(25, sensibilidad));
	}
	
	@Test
	public void usuarioCalurosoNoSeAbrigaCuandoHaceFrio() {
		Sensibilidad sensibilidad = new Sensibilidad();
		sensibilidad.setNivelSensibilidad(ParteCuerpo.TORSO, 50);
		Assert.assertFalse(atuendoNegroConBuzo.esAdecuadoATemperatura(10, sensibilidad));
	}
	
}
