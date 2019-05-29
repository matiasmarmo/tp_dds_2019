package dds.utn.ju_ma.group7.QueMePongo;

import org.junit.Assert;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Evento.EstadoSugerencia;

public class SugerenciaTest extends Fixture {
	
	@Test
	public void aceptarSugerencia() {
		sugerencia.aceptar();
		Assert.assertEquals(EstadoSugerencia.ACEPTADA, sugerencia.getEstado());
	}
	
	@Test
	public void rechazarSugerencia() {
		sugerencia.rechazar();
		Assert.assertEquals(EstadoSugerencia.RECHAZADA, sugerencia.getEstado());
	}
	
	@Test
	public void deshacerOperacion() {
		sugerencia.rechazar();
		sugerencia.deshacerOperacion();
		Assert.assertEquals(EstadoSugerencia.PENDIENTE, sugerencia.getEstado());
	}

}
