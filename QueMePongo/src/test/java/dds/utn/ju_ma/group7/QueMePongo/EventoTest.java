package dds.utn.ju_ma.group7.QueMePongo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Evento.EventoUnico;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Sugerencia;
import dds.utn.ju_ma.group7.QueMePongo.Excepciones.EventoInvalidoException;

public class EventoTest extends Fixture {
	@Before
	public void init() {
		quince = new EventoUnico(usuario, guardarropasVerano, fechaProxima, "Cumple de 15");
	}

	@Test
	public void elEventoNoFueSugerido() {
		Assert.assertEquals(false, quince.fueSugerido(Calendar.getInstance()));
	}

	@Test(expected = EventoInvalidoException.class)
	public void debeSerSugeridoConPorLoMenosUnaSugerencia() {
		List<Sugerencia> vacia = new ArrayList<Sugerencia>();
		quince.serSugerido(vacia);
	}

	@Test
	public void sePuedeSaberSiUnEventoEsProximo() {
		Assert.assertEquals(true, quince.esProximo(fechaProxima));
	}
	
	@Test
	public void unEventoDiarioEsProximo() {
		Assert.assertTrue(irATrabajar.esProximo(Calendar.getInstance()));
	}
	
	@Test
	public void elEventoAnualNoEsProximo() {
		Assert.assertFalse(eventoRepetitivoNoProximo.esProximo(Calendar.getInstance()));
	}
	
	@Test
	public void eventoRepetitivoFueSugerido() {
		eventoInvierno.serSugerido(sugerencias);
		Assert.assertFalse(eventoMensualProximo.fueSugerido(Calendar.getInstance()));
	}
}
