package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.db.WithDbAccess;

public class RepositorioEventosPersistente extends RepositorioEventos implements WithDbAccess {

	@Override
	protected List<Evento> todosLosEventos() {
		return this.entityManager().createQuery("from Evento", Evento.class).getResultList();
	}

	@Override
	protected void almacenar(Evento evento) {
		this.persist(evento);
	}

}
