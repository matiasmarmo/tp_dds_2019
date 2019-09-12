package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioPremium;

public class RepositorioUsuariosPersistente extends RepositorioUsuarios implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps {

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
