package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class RepositorioEventosPersistente extends RepositorioEventos
		implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps {


	@Override
	protected void almacenar(Evento evento) {
		withTransaction(() -> this.persist(evento));
	}
	
	public List<Evento> todosLosEventos() {
		return this.entityManager().createQuery("from Evento", Evento.class).getResultList();
	}

	@Override
	public List<Evento> eventosProximos(Calendar fecha) {
		Calendar en5Dias = ((Calendar) fecha.clone());
		en5Dias.add(Calendar.DAY_OF_MONTH, 5);
		// TODO: Los repetitivos se filtran en memoria. Intentar hacer un query que los
		// filtre.
		String queryString = "from Evento e where e.fecha between :inicio and :fin or e.fechaUltimaInstancia is not null";
		return this.entityManager().createQuery(queryString, Evento.class).setParameter("inicio", fecha)
				.setParameter("fin", en5Dias).getResultList().stream()
				.filter(evento -> evento.esProximo(fecha) && !evento.fueSugerido(fecha)).collect(Collectors.toList());
	}

	@Override
	public List<Evento> eventosDelUsuario(Usuario usuario) {
		String queryString = "from Evento where usuario = :usuario";
		return this.entityManager().createQuery(queryString, Evento.class).setParameter("usuario", usuario)
				.getResultList();
	}

	@Override
	public List<Sugerencia> sugerenciasAceptadasDelUsuario(Usuario usuario) {
		String queryString = "select s from Evento e join e.sugerencias s where e.usuario = :usuario and s.estado = 'ACEPTADA'";
		return this.entityManager().createQuery(queryString, Sugerencia.class)
				.setParameter("usuario", usuario).getResultList();
	}

	@Override
	public List<Sugerencia> sugerenciasRechazadasDelUsuario(Usuario usuario) {
		String queryString = "select s from Evento e join e.sugerencias s where e.usuario = :usuario and s.estado = 'RECHAZADA'";
		return this.entityManager().createQuery(queryString, Sugerencia.class)
				.setParameter("usuario", usuario).getResultList();
	}

	@Override
	public List<Evento> obtenerEventosSugeridosDeUnGuardarropasParaFecha(Guardarropa guardarropa,
			Calendar fechaReferencia) {
		String queryString = "select distinct e from Evento e join e.sugerencias s where e.guardarropa = :guardarropas and e.fecha = :fecha";
		return this.entityManager().createQuery(queryString, Evento.class).setParameter("fecha", fechaReferencia)
				.setParameter("guardarropas", guardarropa).getResultList();
	}

	@Override
	public List<EventoUnico> obtenerEventosEntreFechas(Calendar fechaInicio, Calendar fechaFin) {
		return this.entityManager().createQuery("from Evento", Evento.class).getResultList().stream()
				.flatMap(evento -> evento.instanciasEntreFechas(fechaInicio, fechaFin).stream())
				.collect(Collectors.toList());
	}

}
