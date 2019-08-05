package dds.utn.ju_ma.group7.QueMePongo;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.Alertador;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorMock;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.Sugeridor;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.NotificadorMock;


public class UsuarioTest extends Fixture {

	@Test
	public void seNotificaCuandoSeSugiere() {
		Sugeridor sugeridor = new Sugeridor(new ProveedorMock().setTemperatura(15));
		sugeridor.sugerir(eventoVerano);
		Assert.assertTrue(((NotificadorMock)notificadorUsuario).getFueNotificado());
	}
	
	@Test
	public void seNotificaCuandoHayAlertas() {
		Alertador alertador = new Alertador( new ProveedorMock().setHayTormentas(true).setHayNieve(false));
		alertador.informarAlertas(Calendar.getInstance());
		Assert.assertTrue(((NotificadorMock)notificadorUsuario).getFueNotificadoAlerta());
	}
	
}
