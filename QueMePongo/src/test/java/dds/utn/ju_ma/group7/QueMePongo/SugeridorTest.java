package dds.utn.ju_ma.group7.QueMePongo;

import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorMock;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.Sugeridor;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

public class SugeridorTest extends Fixture {
	
	
	@Test
	public void elAtuendoSinBuzoTieneNivelDeAbrigo40() { 
		Assert.assertEquals(40, atuendoNegro.getNivelAbrigo());
	}

	@Test
	public void elAtuendoConBuzoTieneNivelDeAbrigo70() {
		Assert.assertEquals(70, atuendoNegroConBuzo.getNivelAbrigo());
	}

	@Test
	public void elEventoDeVeranoSoloTieneUnaSugerencia() {
		Sugeridor sugeridor = new Sugeridor(new ProveedorMock(15));
		sugeridor.sugerir(eventoVerano);
		Assert.assertEquals(1, eventoVerano.getSugerencias().size());
	}
	
	@Test
	public void elEventoDeInviernoTieneDosSugerencias() {
		Sugeridor sugeridor = new Sugeridor(new ProveedorMock(10));
		sugeridor.sugerir(eventoInvierno);
		Assert.assertEquals(2, eventoInvierno.getSugerencias().size());
	}
	
	@Test
	public void elEventoDeInviernoEsProximo() {
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.setTime(new Date());
		Assert.assertTrue(eventoInvierno.esProximo(fechaActual));
	}
	
	@Test
	public void elEventoDeVeranoNoEsProximo() {
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.setTime(new Date());
		Assert.assertFalse(eventoVerano.esProximo(fechaActual));
	}
}