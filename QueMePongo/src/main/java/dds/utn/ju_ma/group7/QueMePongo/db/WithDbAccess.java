package dds.utn.ju_ma.group7.QueMePongo.db;

import java.util.Collection;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public interface WithDbAccess extends WithGlobalEntityManager, TransactionalOps, EntityManagerOps {
	
	default <T> void persist(Collection<T> collectionToPersist) {
		this.withTransaction(() -> {
			collectionToPersist.forEach((element) -> this.persist(element));
		});
	}

}
