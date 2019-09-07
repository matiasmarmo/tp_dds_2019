package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;
import dds.utn.ju_ma.group7.QueMePongo.db.WithDbAccess;

public class RepositorioUsuariosPersistente extends RepositorioUsuarios implements WithDbAccess {

	@Override
	protected List<Usuario> todosLosUsuarios() {
		return this.entityManager().createQuery("from Usuario", Usuario.class).getResultList();
	}

	@Override
	protected void almacenar(Usuario usuario) {
		this.withTransaction(() -> {
			this.persist(usuario);
		});
	}

}
