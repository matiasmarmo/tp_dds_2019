package dds.utn.ju_ma.group7.QueMePongo;

import java.util.Arrays;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.Alertador;
import dds.utn.ju_ma.group7.QueMePongo.Alertador.RepositorioUsuarios;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventos;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorMock;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.Sugeridor;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.NotificadorMock;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class UsuarioTest extends Fixture {

	private Usuario usuarioANotificar;
	private NotificadorMock notificador;
	private Evento eventoANotificar;

	@Before
	public void initUsuarioTest() {
		this.notificador = new NotificadorMock();
		this.usuarioANotificar = RepositorioUsuarios.getInstance()
				.instanciarUsuarioGratis(Arrays.asList(this.notificador));
		this.eventoANotificar = RepositorioEventos.getInstance().instanciarEventoUnico(usuarioANotificar,
				guardarropasVerano, fechaLejana, "Un evento para notificar");
	}

	@Test
	public void seNotificaCuandoSeSugiere() {
		Sugeridor sugeridor = new Sugeridor(new ProveedorMock().setTemperatura(15));
		sugeridor.sugerir(this.eventoANotificar);
		Assert.assertTrue(this.notificador.getFueNotificado());
	}

	@Test
	public void seNotificaCuandoHayAlertas() {
		Alertador alertador = new Alertador(new ProveedorMock().setHayTormentas(true).setHayNieve(false));
		alertador.informarAlertas(Calendar.getInstance());
		Assert.assertTrue(this.notificador.getFueNotificadoAlerta());
	}

}
