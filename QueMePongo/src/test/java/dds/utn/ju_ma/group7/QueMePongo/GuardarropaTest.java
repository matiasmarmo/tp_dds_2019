package dds.utn.ju_ma.group7.QueMePongo;

import org.junit.Assert;
import org.junit.Test;

public class GuardarropaTest extends Fixture {

	@Test
	public void unGuardarropaCompletoGeneraAtuendos() {
		Assert.assertEquals(2, guardarropaCompleto.generarAtuendos().size());
	}

	@Test
	public void unGuardarropaDebeEstarCompletoParaGenerarAtuendos() {
		Assert.assertEquals(0, guardarropaIncompleto.generarAtuendos().size());
	}
	
	
}