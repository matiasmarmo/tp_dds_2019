package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioPremium;
import dds.utn.ju_ma.group7.QueMePongo.db.WithDbAccess;

public class RepositorioUsuariosPersistente extends RepositorioUsuarios implements WithDbAccess {

	@Override
	protected List<UsuarioPremium> todosLosUsuarios() {
		return this.entityManager().createQuery("from Usuario", UsuarioPremium.class).getResultList();
	}

	@Override
	protected void almacenar(UsuarioPremium usuario) {
		this.withTransaction(() -> {
			this.persist(usuario);
		});
	}

}
