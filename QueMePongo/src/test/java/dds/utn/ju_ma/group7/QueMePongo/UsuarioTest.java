package dds.utn.ju_ma.group7.QueMePongo;

import org.junit.Assert;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorMock;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.Sugeridor;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.NotificadorMock;


public class UsuarioTest extends Fixture {

	@Test
	public void seNotificaCuadnoSeSugiere() {
		Sugeridor sugeridor = new Sugeridor(new ProveedorMock(15, false, false, false, false, false));
		sugeridor.sugerir(eventoVerano);
		Assert.assertTrue(((NotificadorMock)notificadorUsuario).getFueNotificado());
	}
	
}
