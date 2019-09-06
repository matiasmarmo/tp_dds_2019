package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.Calendar;
import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.InteresEnNotificaciones;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioGratis;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioPremium;
import dds.utn.ju_ma.group7.QueMePongo.db.WithDbAccess;

public class RepositorioUsuarios implements WithDbAccess {

	private static RepositorioUsuarios instance;

	private RepositorioUsuarios() {
	}

	public static RepositorioUsuarios getInstance() {
		if (instance == null) {
			instance = new RepositorioUsuarios();
		}
		return instance;
	}

	public Usuario instanciarUsuarioGratis(List<InteresEnNotificaciones> notificadores) {
		UsuarioGratis usuarioGratis = new UsuarioGratis(notificadores);
		this.persist(usuarioGratis);
		return usuarioGratis;
	}

	public Usuario instanciarUsuarioPremium(List<InteresEnNotificaciones> notificadores) {
		UsuarioPremium usuarioPremium = new UsuarioPremium(notificadores);
		this.persist(usuarioPremium);
		return usuarioPremium;
	}

	public void informarUsuariosDe(Calendar fecha, TipoAlerta alerta) {
		this.entityManager().createQuery("from Usuario", Usuario.class).getResultList()
				.forEach(usuario -> usuario.notificarAlerta(fecha, alerta));
	}

}
