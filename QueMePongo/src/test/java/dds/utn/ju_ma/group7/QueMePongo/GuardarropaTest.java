package dds.utn.ju_ma.group7.QueMePongo;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Excepciones.GuardarropaInvalidoException;
import dds.utn.ju_ma.group7.QueMePongo.Excepciones.GuardarropaLlenoException;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.GuardarropaLimitado;
import static org.mockito.Mockito.when;
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
		GuardarropaLimitado guardarropaLimitado = new GuardarropaLimitado(otroUsuario, repositorioEventosPersistente);
		guardarropaLimitado.agregarPrenda(remeraNegra);
		guardarropaLimitado.agregarPrenda(remeraBlanca);
	}
	
	@Test(expected = GuardarropaInvalidoException.class)
	public void noSePuedeDarAUnUsuarioGratuitoUnGuardarropasNoLimitado() {
		new Guardarropa(otroUsuario, repositorioEventosPersistente);
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
		long cantidadAtuendos = eventoInvierno.getGuardarropa().generarAtuendos(fechaProxima).size();
		//Sugeridor sugeridor = new Sugeridor(new ProveedorMock(10, false, false, false, false, false));
		when(proveedorMock.getTemperatura(clima)).thenReturn(10.0);
		Sugeridor sugeridor = new Sugeridor(proveedorMock);
		sugeridor.sugerir(eventoInvierno);
		eventoInvierno.getSugerencias().get(0).aceptar();
		long nuevaCantidadAtuendos = eventoInvierno.getGuardarropa().generarAtuendos(fechaProxima).size();
		Assert.assertTrue(nuevaCantidadAtuendos < cantidadAtuendos);
	}
	
}