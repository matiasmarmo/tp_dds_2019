package dds.utn.ju_ma.group7.QueMePongo.db;

import java.util.Collection;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public interface WithDbAccess extends WithGlobalEntityManager, TransactionalOps {
	
	default void persist(Object objectToPersist) {
		this.withTransaction(() -> {
			this.entityManager().persist(objectToPersist);
		});
	}
	
	default <T> void persist(Collection<T> collectionToPersist) {
		this.withTransaction(() -> {
			collectionToPersist.forEach((element) -> this.entityManager().persist(element));
		});
	}

}
