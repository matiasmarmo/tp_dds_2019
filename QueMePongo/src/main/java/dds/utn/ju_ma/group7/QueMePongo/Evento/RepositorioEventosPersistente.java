package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class RepositorioEventosPersistente extends RepositorioEventos implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps {

	@Override
	protected List<Evento> todosLosEventos() {
		return this.entityManager().createQuery("from Evento", Evento.class).getResultList();
	}

	@Override
	protected void almacenar(Evento evento) {
		this.persist(evento);
	}

}
