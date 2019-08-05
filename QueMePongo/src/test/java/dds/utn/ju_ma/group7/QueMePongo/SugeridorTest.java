package dds.utn.ju_ma.group7.QueMePongo;

import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.TipoAlerta;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.EstadoDelClima;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorClima;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorMock;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.Sugeridor;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;

public class SugeridorTest extends Fixture {

	@Test
	public void elEventoDeVeranoSoloTieneUnaSugerencia() {
		Sugeridor sugeridor = new Sugeridor(new ProveedorMock(15, false, false, false, false, false));
		sugeridor.sugerir(eventoVerano);
		Assert.assertEquals(1, eventoVerano.getSugerencias().size());
	}
	
	@Test
	public void elEventoDeInviernoTieneDosSugerencias() {
		Sugeridor sugeridor = new Sugeridor(new ProveedorMock(10, false, false, false, false, false));
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
	
	@Test
	public void sePuedenConocerLasAlertarMeteorologicas() {
		ProveedorClima proveedorClima = new ProveedorMock(10, true, false, false, false, false);
		List<TipoAlerta> alertas = new EstadoDelClima(proveedorClima, Calendar.getInstance()).getAlertas();
		Assert.assertEquals(Arrays.asList(TipoAlerta.TORMENTA), alertas);
	}
}