package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.InteresEnNotificaciones;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioGratis;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioPremium;

public class RepositorioUsuarios implements TransactionalOps, WithGlobalEntityManager {

	private static RepositorioUsuarios instance;
	private EntityManager entityManager = this.entityManager();

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
		withTransaction(() -> {
			this.entityManager.persist(usuarioGratis);
		});
		return usuarioGratis;
	}

	public Usuario instanciarUsuarioPremium(List<InteresEnNotificaciones> notificadores) {
		UsuarioPremium usuarioPremium = new UsuarioPremium(notificadores);
		withTransaction(() -> {
			this.entityManager.persist(usuarioPremium);
		});
		return usuarioPremium;
	}

	public void informarUsuariosDe(Calendar fecha, TipoAlerta alerta) {
		this.entityManager.createQuery("from Usuario", Usuario.class).getResultList()
				.forEach(usuario -> usuario.notificarAlerta(fecha, alerta));
	}

}
