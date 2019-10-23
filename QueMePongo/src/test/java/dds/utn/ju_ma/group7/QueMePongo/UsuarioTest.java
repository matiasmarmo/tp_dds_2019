package dds.utn.ju_ma.group7.QueMePongo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.Alertador;
import dds.utn.ju_ma.group7.QueMePongo.Alertador.RepositorioUsuariosMock;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventosMock;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorMock;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.Sugeridor;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.NotificadorMock;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class UsuarioTest extends Fixture {

	private RepositorioUsuariosMock repositorioUsuariosMock;
	private Usuario usuarioANotificar;
	private NotificadorMock notificador;
	private Evento eventoANotificar;

	@Before
	public void initUsuarioTest() {
		this.repositorioUsuariosMock = new RepositorioUsuariosMock();
		this.notificador = new NotificadorMock();
		this.usuarioANotificar = this.repositorioUsuariosMock
				.instanciarUsuarioGratis(Arrays.asList(this.notificador), "", "");
		this.eventoANotificar = new RepositorioEventosMock().instanciarEventoUnico(usuarioANotificar,
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
		Alertador alertador = new Alertador(new ProveedorMock().setHayTormentas(true).setHayNieve(false), this.repositorioUsuariosMock);
		alertador.informarAlertas(Calendar.getInstance());
		Assert.assertTrue(this.notificador.getFueNotificadoAlerta());
	}
	
	@Test
	public void sePuedePersistirElGuardarropasDeUnUsuario() {
		this.entityManager().persist(usuario);
		List<Usuario> usuariosPersistidos = this.entityManager().createQuery("from Usuario", Usuario.class).getResultList();
		Usuario usuarioPersistido = usuariosPersistidos.stream()
				.filter(unUsuario-> usuario == unUsuario)
				.collect(Collectors.toList()).get(0);
		Assert.assertTrue(usuario.getGuardarropas().get(0) == usuarioPersistido.getGuardarropas().get(0));
	}

}
