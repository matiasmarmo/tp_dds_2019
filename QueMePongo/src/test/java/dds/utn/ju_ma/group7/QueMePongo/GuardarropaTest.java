package dds.utn.ju_ma.group7.QueMePongo;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Excepciones.GuardarropaInvalidoException;
import dds.utn.ju_ma.group7.QueMePongo.Excepciones.GuardarropaLlenoException;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.GuardarropaLimitado;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorMock;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.Sugeridor;

public class GuardarropaTest extends Fixture {

	@Test
	public void unGuardarropaCompletoGeneraAtuendos() {
		Assert.assertEquals(2, guardarropaCompleto.generarAtuendos(Calendar.getInstance()).size());
	}

	@Test
	public void unGuardarropaDebeEstarCompletoParaGenerarAtuendos() {
		Assert.assertEquals(0, guardarropaIncompleto.generarAtuendos(Calendar.getInstance()).size());
	}
	
	@Test(expected = GuardarropaLlenoException.class)
	public void unGuardarropaLimitadoNoPuedeAlmacenarMasPrendasQueElLimite() {
		GuardarropaLimitado guardarropaLimitado = new GuardarropaLimitado(otroUsuario);
		guardarropaLimitado.agregarPrenda(remeraNegra);
		guardarropaLimitado.agregarPrenda(remeraBlanca);
	}
	
	@Test(expected = GuardarropaInvalidoException.class)
	public void noSePuedeDarAUnUsuarioGratuitoUnGuardarropasNoLimitado() {
		new Guardarropa(otroUsuario);
	}
	
	@Test
	public void losUsuariosPuedenCompartirGuardarropas() {
		Assert.assertTrue(usuario.tieneAccesoAGuardarropas(guardarropaCompartido));
		Assert.assertTrue(otroUsuario.tieneAccesoAGuardarropas(guardarropaCompartido));
		Assert.assertTrue(guardarropaCompartido.usuarioTieneAcceso(usuario));
		Assert.assertTrue(guardarropaCompartido.usuarioTieneAcceso(otroUsuario));
	}
	
	@Test
	public void lasPrendasEnUsoNoSonSugeridas() {
		long cantidadAtuendos = eventoInvierno.getGuardarropa().generarAtuendos(manianaCalendar).size();
		Sugeridor sugeridor = new Sugeridor(new ProveedorMock(10, false, false, false, false, false));
		sugeridor.sugerir(eventoInvierno);
		eventoInvierno.getSugerencias().get(0).aceptar();
		long nuevaCantidadAtuendos = eventoInvierno.getGuardarropa().generarAtuendos(manianaCalendar).size();
		Assert.assertTrue(nuevaCantidadAtuendos < cantidadAtuendos);
	}
	
}