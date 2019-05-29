package dds.utn.ju_ma.group7.QueMePongo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.EstadoSugerencia;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Sugerencia;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.PrendaBuilder;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoTela;

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
