package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class RepositorioUsuariosPersistente extends RepositorioUsuarios
		implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps {

	@Override
	protected List<Usuario> todosLosUsuarios() {
		return this.entityManager().createQuery("from Usuario", Usuario.class).getResultList();
	}

	@Override
	public void almacenar(Usuario usuario) {
		this.persist(usuario);
	}

}
